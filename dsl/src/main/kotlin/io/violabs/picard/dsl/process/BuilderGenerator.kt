package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.writeTo
import io.violabs.picard.common.VLoggable
import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.dsl.builder.*
import io.violabs.picard.dsl.config.BuilderConfig
import io.violabs.picard.dsl.config.DomainConfig
import io.violabs.picard.dsl.props.DslPropSchema

interface BuilderGenerator {
    fun generate(
        codeGenerator: CodeGenerator,
        domain: KSClassDeclaration,
        builderConfig: BuilderConfig,
        singleEntryTransformByClassName: Map<String, KSClassDeclaration>,
    )
}

class DefaultBuilderGenerator(
    val parameterService: DefaultPropertySchemaService = DefaultPropertySchemaService()
) : BuilderGenerator, VLoggable {
    override fun logId(): String? = "BLDR_GENERATOR"

    init {
        logger.enableDebug()
    }

    override fun generate(
        codeGenerator: CodeGenerator,
        domain: KSClassDeclaration,
        builderConfig: BuilderConfig,
        singleEntryTransformByClassName: Map<String, KSClassDeclaration>,
    ) {
        val domainConfig = DomainConfig(
            builderConfig,
            singleEntryTransformByClassName,
            domain
        )
        generateFilesForDsl(domainConfig, codeGenerator)
    }

    private fun generateFilesForDsl(
        domainConfig: DomainConfig,
        codeGenerator: CodeGenerator
    ) {
        logger.debug("-- generating builder --", tier = 0)
        logger.debug("+++ DOMAIN: ${domainConfig.domainClassName}  +++")
        logger.debug("package: ${domainConfig.packageName}", tier = 1, branch = true)
        logger.debug("type: ${domainConfig.typeName}", tier = 1, branch = true)
        logger.debug("builder: ${domainConfig.builderName}", tier = 1, branch = true)

        val params = parameterService.getParamsFromDomain(domainConfig)

        val fileContent = generateBuilderFileContent(domainConfig, params)

        // Check if any validation functions are needed.
        // This logic assumes propertyValueReturn() generates the vRequireNotNull etc. calls.
        val hasRequireNotNull = params.any { param -> !param.nullableAssignment && param.verifyNotNull }
        val hasRequireNotEmpty = params.any { param -> !param.nullableAssignment && param.verifyNotEmpty }
        logger.debug("requiresNotNull: $hasRequireNotNull", tier = 1, branch = true)
        logger.debug("requiresNotEmpty: $hasRequireNotEmpty", tier = 1, branch = true)

        val fileSpec = kotlinPoet {
            file {
                addImportIf(hasRequireNotNull, "io.violabs.picard.dsl", "vRequireNotNull")
                addImportIf(hasRequireNotEmpty, "io.violabs.picard.dsl", "vRequireNotEmpty")
                className = domainConfig.fileClassName
                types(fileContent)
            }
        }

        fileSpec.writeTo(codeGenerator, domainConfig.dependencies)
        logger.debug("file written: ${domainConfig.fileClassName}", tier = 1)
    }

    private fun generateBuilderFileContent(
        domainConfig: DomainConfig,
        params: List<DslPropSchema>
    ): TypeSpec = kotlinPoet {
        val domainClassName = domainConfig.domainClassName
        val domain = domainConfig.domain

        type {
            annotation { createDslMarkerIfAvailable(domainConfig.builderConfig.dslMarkerClass) }
            public()
            name = domainConfig.builderName
            superInterface(domainConfig.parameterizedDslBuilder)
            logger.debug("DSL Builder Interface added", tier = 1, branch = true)
            logger.debug("Properties added", tier = 1)

            properties {
                params.addForEach(DslPropSchema::toPropertySpec)
            }

            functions {
                params.addForEach(DslPropSchema::accessors)

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

            val isGroup = domain
                .annotations
                .filter { it.shortName.asString() == GeneratedDsl::class.simpleName.toString() }
                .flatMap { it.arguments }
                .filter { it.name?.asString() == GeneratedDsl::withGroup.name }
                .onEach { logger.debug("found arg: ${it.name?.asString()} - ${it.value}", tier = 1) }
                .any { it.value.toString() == "true" }

            logger.debug("[DECISION] isGroup: $isGroup", tier = 1)

            val isMapBuilderGroup = domain
                .annotations
                .filter { it.shortName.asString() == GeneratedDsl::class.simpleName.toString() }
                .flatMap { it.arguments }
                .filter { it.name?.asString() == GeneratedDsl::withMapGroup.name }
                .onEach { logger.debug("found arg: ${it.name?.asString()} - ${it.value}", tier = 1) }
                .any { argument ->
                    val activeTypes = GeneratedDsl.MapGroupType.ACTIVE_TYPES.map { it.name }
                    argument.value?.toString() in activeTypes
                }
            logger.debug("[DECISION] isMapGroup: $isGroup", tier = 1)

            if (isGroup) {
                logger.debug("group domain", tier = 1, branch = true)
                val builderClassName = domainConfig.builderClassName

                nested {
                    addType {
                        name = "Group"
                        annotation {
                            createDslMarkerIfAvailable(domainConfig.builderConfig.dslMarkerClass)
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
            }

            if (isMapBuilderGroup) {
                logger.debug("map group domain", tier = 1, branch = true)

                nested {
                    val typeVariable = TypeVariableName("T")
                    addType {
                        name = "MapGroup"
                        typeVariables(typeVariable)
                        annotation {
                            createDslMarkerIfAvailable(domainConfig.builderConfig.dslMarkerClass)
                        }
                        properties {
                            add {
                                private()
                                name = "items"
                                type(kpMutableMapOf(typeVariable, domainClassName, nullable = false))
                                initializer = "mutableMapOf()"
                            }
                        }
                        functions {
                            add {
                                funName = "items"
                                returns = kpMapOf(typeVariable, domainClassName, nullable = false)
                                statements {
                                    addLine("return items.toMap()")
                                }
                            }

                            add {
                                funName = domainClassName.simpleName.replaceFirstChar { it.lowercase() }
                                param {
                                    name = "key"
                                    type(typeVariable, nullable = false)
                                }
                                param {
                                    lambdaType {
                                        receiver = domainConfig.builderClassName
                                    }
                                }
                                statements {
                                    addLine("items[key] = %T().apply(block).build()", domainConfig.builderClassName)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createDslMarkerIfAvailable(dslMarkerClasspath: String?): ClassName? {
        if (dslMarkerClasspath == null) return null

        logger.debug("DSL Marker added", tier = 1, branch = true)
        val split = dslMarkerClasspath.split(".")
        val dslMarkerPackageName = split.subList(0, split.size - 1).joinToString(".")
        val dslMarkerSimpleName = split.last()
        return ClassName(dslMarkerPackageName, dslMarkerSimpleName)
    }
}