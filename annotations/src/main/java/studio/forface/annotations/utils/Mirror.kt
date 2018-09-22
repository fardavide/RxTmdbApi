package studio.forface.annotations.utils

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.asTypeName
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.*
import javax.lang.model.type.TypeMirror
import kotlin.reflect.KClass


fun TypeElement.getvalue( processingEnv: ProcessingEnvironment, annotationClass: KClass<*>, value: String ): TypeElement? {
    val am = getAnnotationMirror(this, annotationClass.java ) ?: return null
    val av = getAnnotationValue( am, value )
    val typeMirror = av?.value as? TypeMirror
    return typeMirror?.asTypeElement( processingEnv )
}


private fun getAnnotationMirror( typeElement: TypeElement, clazz: Class<*> ): AnnotationMirror? =
        typeElement.annotationMirrors.find { it.annotationType.toString() == clazz.name }


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

private fun TypeMirror.asTypeElement( processingEnv: ProcessingEnvironment ): TypeElement =
        processingEnv.typeUtils.asElement( this ) as TypeElement
