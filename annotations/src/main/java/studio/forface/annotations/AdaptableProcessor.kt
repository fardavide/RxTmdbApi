package studio.forface.annotations

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import studio.forface.annotations.utils.*
import java.io.File
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMembers

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
            @Suppress("UNCHECKED_CAST") val types = annotatedClass.getvalue(
                    processingEnv, AdaptableClass::class,"generatedTypes"
            ) as? Array<KClass<*>> ?: arrayOf( Observable::class )

            // Take the size of the smaller Array in Annotation.
            val reducedCount = Math.min( prefixes.size, types.size )

            // Generate classes / interface for every prefix-type couple in the Annotation.
            ( 0 until reducedCount ).mapIndexed { _, i ->
                generateClass( annotatedClass, prefixes[i], types[i] )
            }.forEach { fileSpec ->
                val options = processingEnv.options
                val generatedPath = options[PATH_GENERATED]
                val path = generatedPath?.replace(
                        "(.*)tmp(/kapt/debug/)kotlinGenerated".toRegex(),
                        "$1generated/source$2"
                )
                fileSpec.writeTo( File( path, "${fileSpec.name}.kt" ) )
            }
        }

        return true
    }

    private fun generateClass( sourceClass: TypeElement, prefix: String, type: KClass<*> ): FileSpec {
        // Generate the final interface name with prefix.
        val className = prefix + sourceClass.simpleName
        // Initialize the builder with the className.
        val builder = TypeSpec.interfaceBuilder( className )

        sourceClass.enclosedElements.mapNotNull { it as? ExecutableElement }.forEach { method ->

            // Get method annotations.
            val annotations = method.buildAnnotationsCode()

            // Get method params.
            val params = method.parameters.map { param ->
                val paramAnnotations = param.buildAnnotationsCode()
                ParameterSpec.builder( param.simpleName.toString(), Any::class ) // TODO: replace Any::class // Todo: set modifiers
                        .addAnnotations( paramAnnotations )
                        .build()
            }

            // Add the Function to the builder.
            builder.addFunction(
                    FunSpec.builder( method.simpleName.toString() )
                            .addModifiers( KModifier.ABSTRACT )
                            .addAnnotations( annotations )
                            .addParameters( params )
                            .returns( type )
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