package studio.forface.annotations

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.sun.tools.javac.code.Attribute
import com.sun.tools.javac.code.Type
import studio.forface.annotations.utils.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy


private const val PATH_GENERATED = "kapt.kotlin.generated"

@AutoService( Processor::class )
class AdaptableProcessor: AbstractProcessor() {

    /**
     * An instance of [Messager] for write output in Build process.
     */
    private lateinit var messager: Messager

    @Synchronized
    override fun init( processingEnv: ProcessingEnvironment ) {
        super.init( processingEnv )
        messager = processingEnv.messager
    }

    override fun process( annotations: Set<TypeElement>, roundEnv: RoundEnvironment ): Boolean {
        val message = "AdaptableProcessor is running"
        messager.printMessage( Diagnostic.Kind.NOTE, message )
        println( message )

        // Getting all the class annotated with AdaptableClass.
        roundEnv.getElementsAnnotatedWith( AdaptableClass::class.java ).forEach { annotatedElement ->
            // Cast the element to a TypeElement ( class or interface ).
            val annotatedClass = annotatedElement as TypeElement

            // Get the annotation for use its values.
            val annotation = annotatedClass.getAnnotation( AdaptableClass::class.java )

            // Get the values of the Annotation.
            val prefixes = annotation.generatedClassesPrefix

            /*val a =annotatedClass.getValue<Int>(
                    processingEnv, AdaptableClass::class,"generatedTypes"
            )!!*/

            val types = annotatedClass.getValue<List<Attribute.Class>>(
                    processingEnv, AdaptableClass::class,"generatedTypes"
            )!!.map { it.value as Type.ClassType }

            // Take the size of the smaller Array in Annotation.
            val reducedCount = Math.min( prefixes.size, types.size )

            // Generate classes / interface for every prefix-type couple in the Annotation.
            ( 0 until reducedCount ).mapIndexed { _, i ->
                generateClass( annotatedClass, prefixes[i], types[i] )
            }.forEach { fileSpec ->
                val options = processingEnv.options
                val generatedPath = options[PATH_GENERATED]
                val path = generatedPath
                        ?.replace("kaptKotlin","kapt" )
                        ?.replace( fileSpec.name,"" )
                        ?.replace(".kt","" )

                fileSpec.writeTo( File( path, "${fileSpec.name}.kt" ) )
            }
        }

        return true
    }

    private fun generateClass( sourceClass: TypeElement, prefix: String, type: Type.ClassType ): FileSpec {
        // Generate the final interface name with prefix.
        val className = prefix + sourceClass.simpleName
        // Initialize the builder with the className.
        val builder = TypeSpec.interfaceBuilder( className )

        sourceClass.enclosedElements.mapNotNull { it as? ExecutableElement }.forEach { method ->
            // Get the return Type name.
            val typeName = method.returnType.asTypeName()
            // Wrap the original return type into type.
            val returnType = type.asClassSymbol( processingEnv ).asClassName()
                    .parameterizedBy( typeName )

            // Build method annotations.
            val annotations = method.buildAnnotationsCode()

            // Get method params.
            val params = method.parameters.map { param ->
                val paramAnnotations = param.buildAnnotationsCode()
                ParameterSpec.builder( param.simpleName.toString(), param.asType().asTypeName().asNullable() )
                        .addAnnotations( paramAnnotations )
                        .defaultValue( "null" )
                        .build()
            }

            // Add the Function to the builder.
            builder.addFunction(
                    FunSpec.builder( method.simpleName.toString() )
                            .addModifiers( KModifier.ABSTRACT )
                            .addAnnotations( annotations )
                            .addParameters( params )
                            .returns( returnType )
                            .build()
            )
        }

        val packageName = "studio.forface.rxtmdbapi.tmdb"
        return FileSpec.builder( packageName, className )
                .addType( builder.build() )
                .build()
    }

    override fun getSupportedAnnotationTypes() =
            setOf( AdaptableClass::class.java.canonicalName )

    override fun getSupportedSourceVersion(): SourceVersion =
            SourceVersion.latestSupported()
}