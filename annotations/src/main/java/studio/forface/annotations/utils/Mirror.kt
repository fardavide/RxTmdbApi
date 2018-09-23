package studio.forface.annotations.utils

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.asTypeName
import com.sun.tools.javac.code.Symbol
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.*
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass


fun <T> TypeElement.getValue( processingEnv: ProcessingEnvironment, annotationClass: KClass<*>, value: String ): T? {
    val am = getAnnotationMirror(this, annotationClass ) ?: return null
    val av = getAnnotationValue( am, value )
    @Suppress("UNCHECKED_CAST")
    return av?.value as T?
}


private fun getAnnotationMirror( typeElement: TypeElement, klass: KClass<*> ): AnnotationMirror? =
        typeElement.annotationMirrors.find { it.annotationType.toString() == klass.qualifiedName }


private fun getAnnotationValue( annotationMirror: AnnotationMirror, key: String ): AnnotationValue? {
    annotationMirror.elementValues.forEach {
        if ( it.key.simpleName.toString() == key ) return it.value
    }
    return null
}

fun Element.buildAnnotationsCode() = annotationMirrors.mapNotNull { mirror ->
    retrofitAnnotations.find { it.qualifiedName == mirror.annotationType.toString() }?.let {
        it to mirror
    }
}.map { pair ->
    val ( klass, annotation ) = pair
    val aBuilder = AnnotationSpec.builder( klass )

    annotation.elementValues.forEach { value ->
        val code = CodeBlock.builder()
                .addStatement( value.codeValue )
                .build()
        aBuilder.addMember( code )
    }

    aBuilder.build()
}

internal val Map.Entry<ExecutableElement, AnnotationValue>.returnTypeName get() =
    key.returnType.asTypeName().toString()

internal val Map.Entry<ExecutableElement, AnnotationValue>.codeValue get() =
    if ( returnTypeName.contains("String" ) ) "\"${value.value}\"" else value.value.toString()

internal fun TypeMirror.asTypeElement( processingEnv: ProcessingEnvironment ): TypeElement =
        processingEnv.typeUtils.asElement( this ) as TypeElement

internal fun TypeMirror.asClassSymbol( processingEnv: ProcessingEnvironment ): Symbol.ClassSymbol =
        processingEnv.typeUtils.asElement( this ) as Symbol.ClassSymbol

internal fun TypeMirror.asTypeVariableSymbol( processingEnv: ProcessingEnvironment ): Symbol.TypeVariableSymbol =
        processingEnv.typeUtils.asElement( this ) as Symbol.TypeVariableSymbol
