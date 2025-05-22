package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ksp.toClassName
import io.violabs.picard.common.Colors
import io.violabs.picard.common.VLoggable
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL
import io.violabs.picard.dsl.config.BuilderConfig
import kotlin.reflect.KClass

interface DslGenerator<PARAM_ADAPTER : ParameterFactoryAdapter, PROP_ADAPTER : PropertyAdapter> {
    val propertyFactory: PropertyFactory<PARAM_ADAPTER, PROP_ADAPTER>
    val builderGenerator: BuilderGenerator

    fun generate(resolver: Resolver, codeGenerator: CodeGenerator, options: Map<String, String?> = emptyMap())
}

class DefaultDslGenerator(
    override val propertyFactory: DefaultPropertyFactory = DefaultPropertyFactory(),
    override val builderGenerator: DefaultBuilderGenerator = DefaultBuilderGenerator()
) : DslGenerator<DefaultParameterFactoryAdapter, DefaultPropertyAdapter>, VLoggable {
    override fun logId(): String? = "DSL_GENERATOR"

    /**
     * This will generate Custom DSL Builders based on the annotation, as well as the
     * test classes that can verify the data.
     * Keywords:
     * - User Defined DSL: the use of the [GeneratedDSL] annotation to define a custom DSL
     * - DSL Builder: the engine that creates the DSL per use
     * - DSL Marker: the provided annotation for the used library (also restricts scope)
     * - Resolved Class: the user defined class that the [GeneratedDSL] will decorate
     * - Builder Class: the generated class that should match the specs of the Resolved Class
     * - Group Class: A list of builders - requires a specific implementation
     * - Map Group Class: a map of builders - requires a specific implementation
     * todo: List<List<*>>, Map<T, List<*>>, Map<T, Map<T, *>
     * @param resolver symbol resolver for ksp interaction
     * @param codeGenerator creates and manages files with ksp
     * @param options compile time options defined in the host project:
     * - **dslBuilderClasspath** ~required~ the classpath to the custom DslBuilder class
     * - **dslMarkerClass** ~required~ the class that contains a [DslMarker] to denote the host dsl name.
     */
    override fun generate(resolver: Resolver, codeGenerator: CodeGenerator, options: Map<String, String?>) {
        logger.debug("------- GENERATE", tier = 0)
        val builderConfig = BuilderConfig(options, logger)

        val generatedBuilderDSL = getGeneratedDslAnnotation(resolver)

        val singleEntryTransformByClassName: Map<String, KSClassDeclaration> =
            getSingleEntryTransformByClassName(resolver)

        generatedBuilderDSL.forEach { domain ->
            builderGenerator.generate(codeGenerator, domain, builderConfig, singleEntryTransformByClassName)
        }
    }

    private fun getGeneratedDslAnnotation(resolver: Resolver): Sequence<KSClassDeclaration> {
        return getClassDeclarationByAnnotation(resolver, GeneratedDSL::class)
    }

    private fun getSingleEntryTransformByClassName(resolver: Resolver): Map<String, KSClassDeclaration> {
        return getClassDeclarationByAnnotation(resolver, SingleEntryTransformDSL::class)
            .associateBy { it.toClassName().toString() }
    }

    private fun getClassDeclarationByAnnotation(resolver: Resolver, klass: KClass<*>): Sequence<KSClassDeclaration> {
        return resolver
            .getSymbolsWithAnnotation(klass.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .onEach { logger.debug("Found ${Colors.yellow("@${klass.simpleName}")} on ${it.simpleName.asString()}") }
    }
}