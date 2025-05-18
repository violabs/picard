package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.joinToCode
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
import io.violabs.picard.dsl.params.DSLParam

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

            val lastIndex = domain.getAllProperties().count() - 1

            val params = domain
                .getAllProperties()
                .mapIndexed { i, prop -> DefaultPropertyAdapter(i, lastIndex, prop, singleEntryTransform) }
                .map(parameterFactory::createParameterFactoryAdapter)
                .map(parameterFactory::determineParam)
                .toList()

            val fileContent = kotlinPoet {
                type {
                    annotation { createDslMarkerIfAvailable(dslMarkerClasspath) }
                    public()
                    name = builderName
                    superInterface(parameterizedDslBuilder)
                    LOGGER.debug("DSL Builder Interface added", tier = 1, branch = true)
                    LOGGER.debug("Properties added", tier = 1)

                    properties {
                        params.addForEach(DSLParam::toPropertySpec)
                    }

                    functions {
                        params.addForEach(DSLParam::accessors)

                        add {
                            override()
                            funName = "build"
                            returns = domainClassName

                            statements {
                                val constructorParams = params
                                    .map { CodeBlock.of("%N = %L", it.propName, it.propertyValueReturn()) }
                                if (constructorParams.isEmpty()) {
                                    addLine("return %T()", domainClassName)
                                } else {
                                    val argumentsBlock = constructorParams.joinToCode(
                                        separator = ",\n    ",
                                        prefix = "\n    ",
                                        suffix = "\n"
                                    )
                                    addLine("return %T(%L)", domainClassName, argumentsBlock)
                                }
                            }
                        }
                    }

                    val isGroup = domain.annotations
                        .any { it.shortName.asString() == GeneratedGroupDSL::class.simpleName.toString() }

                    if (isGroup) {
                        LOGGER.debug("group domain", tier = 1, branch = true)
                        val builderClassName = ClassName(pkg, builderName)

                        nested {
                            addType {
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
                    } else {
                        LOGGER.debug("single domain", tier = 1, branch = true)
                    }
                }
            }

            // Check if any validation functions are needed.
            // This logic assumes propertyValueReturn() generates the vRequireNotNull etc. calls.
            val requiresVRequireNotNull = params.any { param -> !param.nullableAssignment && param.verifyNotNull }
            val requiresVRequireNotEmpty = params.any { param -> !param.nullableAssignment && param.verifyNotEmpty }
            LOGGER.debug("requiresNotNull: $requiresVRequireNotNull", tier = 1, branch = true)
            LOGGER.debug("requiresNotEmpty: $requiresVRequireNotEmpty", tier = 1, branch = true)

            val fileSpec = kotlinPoet {
                file {
                    addImportIf(requiresVRequireNotNull, "io.violabs.picard.dsl", "vRequireNotNull")
                    addImportIf(requiresVRequireNotEmpty, "io.violabs.picard.dsl", "vRequireNotEmpty")
                    className = ClassName(pkg, "${typeName}Dsl")
                    types(fileContent)
                }
            }

            fileSpec
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