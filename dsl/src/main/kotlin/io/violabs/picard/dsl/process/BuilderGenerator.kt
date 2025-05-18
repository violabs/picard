package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import io.violabs.picard.common.Colors
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.GeneratedGroupDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL
import io.violabs.picard.dsl.builder.kotlinPoet
import io.violabs.picard.dsl.builder.kpListOf
import io.violabs.picard.dsl.builder.kpMutableListOf

private val LOGGER = Logger("DSL_BUILDER")
    .enableDebug()
    .disableWarning()

class BuilderGenerator(
    val parameterFactory: ParameterFactory<DefaultParameterFactoryAdapter, DefaultPropertyAdapter> =
        DefaultParameterFactory(LOGGER)
) {

    fun generate(resolver: Resolver, codeGenerator: CodeGenerator, options: Map<String, String> = emptyMap()) {
        LOGGER.debug("------- GENERATE", tier = 0)
        val dslBuilderClasspath = options["dslBuilder.classpath"]
        val dslMarkerClasspath = options["dslMarker.classpath"]
        LOGGER.debug("dslBuilderClasspath: $dslBuilderClasspath")
        LOGGER.debug("dslMarkerClasspath:  $dslMarkerClasspath")

        if (dslBuilderClasspath == null) {
            LOGGER.error("KSP Option 'dslBuilder.classpath' is not defined. Please set it in your build.gradle.")
            return
        }

        val generatedBuilderDSL = resolver
            .getSymbolsWithAnnotation(GeneratedDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .onEach { LOGGER.debug("Found ${Colors.yellow("@GeneratedDSL")} on ${it.simpleName.asString()}") }

        val singleEntryTransform: Map<String, KSClassDeclaration> = resolver
            .getSymbolsWithAnnotation(SingleEntryTransformDSL::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .onEach { LOGGER.debug("Found ${Colors.yellow("@SingleEntryTransformDSL")} on ${it.simpleName.asString()}") }
            .associateBy { it.toClassName().toString() }

        generatedBuilderDSL.forEach { domain ->
            LOGGER.debug("-- generating builder --", tier = 0)
            val pkg = domain.packageName.asString()
            val typeName = domain.simpleName.asString()
            val builderName = "${typeName}Builder"
            val domainClassName: ClassName = domain.toClassName()

            LOGGER.debug("+++ DOMAIN: $domainClassName  +++")
            LOGGER.debug("package: $pkg", tier = 1, branch = true)
            LOGGER.debug("type: $typeName", tier = 1, branch = true)
            LOGGER.debug("builder: $builderName", tier = 1, branch = true)

            val dslBuilderInterface = ClassName(dslBuilderClasspath, "DSLBuilder")
            val parameterizedDslBuilder = dslBuilderInterface.parameterizedBy(domainClassName)

//            val bc = kotlinPoet {
//                type {
//                    public()
//                    annotation { createDslMarkerIfAvailable(dslMarkerClasspath) }
//                    superInterface(parameterizedDslBuilder)
//                }
//            }

            val builderClass: TypeSpec.Builder = TypeSpec.classBuilder(builderName)
                .addModifiers(KModifier.PUBLIC) // Typically builders are public

            // add DSL Marker to the top of the class to restrict scope. Provided by consumer.
            createDslMarkerIfAvailable(dslMarkerClasspath)?.let { builderClass.addAnnotation(it) }

            builderClass.addSuperinterface(parameterizedDslBuilder)
            LOGGER.debug("DSL Builder Interface added", tier = 1, branch = true)

            LOGGER.debug("Properties added", tier = 1)
            val lastIndex = domain.getAllProperties().count() - 1

            val params = domain
                .getAllProperties()
                .mapIndexed { i, prop -> DefaultPropertyAdapter(i, lastIndex, prop, singleEntryTransform) }
                .map(parameterFactory::createParameterFactoryAdapter)
                .map(parameterFactory::determineParam)
                .toList()

            params.forEach { builderClass.addProperty(it.toPropertySpec()) }

            params.forEach { builderClass.addFunctions(it.accessors()) }

            val constructorParams = params
                .map { CodeBlock.of("%N = %L", it.propName, it.propertyValueReturn()) }

            val buildFun = FunSpec.builder("build")
                .addModifiers(KModifier.OVERRIDE)
                .returns(domainClassName)

            if (constructorParams.isEmpty()) {
                buildFun.addStatement("return %T()", domainClassName)
            } else {
                // joinToCode is cleaner for multi-line constructor calls
                val argumentsBlock =
                    constructorParams.joinToCode(separator = ",\n    ", prefix = "\n    ", suffix = "\n")
                buildFun.addStatement("return %T(%L)", domainClassName, argumentsBlock)
            }
            builderClass.addFunction(buildFun.build())

            val isGroup = domain.annotations
                .any { it.shortName.asString() == GeneratedGroupDSL::class.simpleName.toString() }

            if (isGroup) {
                LOGGER.debug("group domain", tier = 1, branch = true)
                val builderClassName = ClassName(pkg, builderName)

                val nested = kotlinPoet {
                    type {
                        name = "Group"
                        annotation {
                            createDslMarkerIfAvailable(dslMarkerClasspath)
                        }
                        properties {
                            add {
                                private()
                                name = "items"
                                type(kpMutableListOf(domainClassName, nullable = false))
                                initializer = "mutableListOf()"
                            }
                        }
                        functions {
                            add {
                                funName = "items"
                                returns = kpListOf(domainClassName, nullable = false)
                                statements {
                                    addLine("return items.toList()")
                                }
                            }

                            add {
                                funName = domainClassName.simpleName.replaceFirstChar { it.lowercase() }
                                param {
                                    lambdaType {
                                        receiver = builderClassName
                                    }
                                }
                                statements {
                                    addLine("items.add(%T().apply(block).build())", builderClassName)
                                }
                            }
                        }
                    }
                }

                builderClass.addType(nested)
            } else {
                LOGGER.debug("single domain", tier = 1, branch = true)
            }

            // Check if any validation functions are needed.
            // This logic assumes propertyValueReturn() generates the vRequireNotNull etc. calls.
            val requiresVRequireNotNull = domain.getAllProperties().any { prop ->
                val adapter = DefaultParameterFactoryAdapter(prop, null)
                val dslP = parameterFactory.determineParam(adapter, false)
                !dslP.nullableAssignment && dslP.verifyNotNull
            }
            val requiresVRequireNotEmpty = domain.getAllProperties().any { prop ->
                val adapter = DefaultParameterFactoryAdapter(prop, null)
                val dslP = parameterFactory.determineParam(adapter, false)
                !dslP.nullableAssignment && dslP.verifyNotEmpty
            }
            LOGGER.debug("requiresNotNull: $requiresVRequireNotNull", tier = 1, branch = true)
            LOGGER.debug("requiresNotEmpty: $requiresVRequireNotEmpty", tier = 1, branch = true)

            val fileSpecBuilder = FileSpec
                .builder(pkg, "${typeName}Dsl") // File name
                .addType(builderClass.build())
                .indent("    ")

            if (requiresVRequireNotNull) {
                // Assuming these are top-level functions or extension functions
                fileSpecBuilder.addImport("io.violabs.picard.dsl", "vRequireNotNull")
            }
            if (requiresVRequireNotEmpty) {
                fileSpecBuilder.addImport("io.violabs.picard.dsl", "vRequireNotEmpty")
            }

            fileSpecBuilder
                .build()
                .writeTo(
                    codeGenerator,
                    Dependencies(aggregating = false, sources = listOfNotNull(domain.containingFile).toTypedArray())
                )
            LOGGER.debug("file written: ${pkg}.${typeName}Dsl", tier = 1)
        }
    }

    private fun createDslMarkerIfAvailable(dslMarkerClasspath: String?): ClassName? {
        if (dslMarkerClasspath == null) return null

        LOGGER.debug("DSL Marker added", tier = 1, branch = true)
        val split = dslMarkerClasspath.split(".")
        val dslMarkerPackageName = split.subList(0, split.size - 1).joinToString(".")
        val dslMarkerSimpleName = split.last()
        return ClassName(dslMarkerPackageName, dslMarkerSimpleName)
    }
}