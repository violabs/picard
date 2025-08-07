package io.violabs.picard.domain.k8sResources.extend.json


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class JSONSchemaPropsTest : SuccessBuildSim<JSONSchemaProps, JSONSchemaPropsDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            JSONSchemaPropsTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NESTED_PROP = JSONSchemaProps(
            ref = PLACEHOLDER,
            schema = PLACEHOLDER
        )
        private val JSON_SCHEMA_PROPS_OR_STRING_ARRAY = JSONSchemaPropsOrStringArray(
            props = NESTED_PROP,
            array = listOf(PLACEHOLDER)
        )
        private val DEFAULT_JSON = JSON(PLACEHOLDER)

        private fun JSONSchemaPropsDslBuilder.nestedProp() {
            ref = PLACEHOLDER
            schema = PLACEHOLDER
        }

        private val SUCCESS_POSSIBILITIES = possibilities<JSONSchemaProps, JSONSchemaPropsDslBuilder> {
            scenario {
                id = "full"
                given(JSONSchemaPropsDslBuilder()) {
                    ref = PLACEHOLDER
                    schema = PLACEHOLDER
                    additionalItems {
                        props { nestedProp() }
                        bool()
                    }
                    allOf {
                        addJSONSchemaProps { nestedProp() }
                    }
                    anyOf {
                        addJSONSchemaProps { nestedProp() }
                    }
                    default(PLACEHOLDER)
                    definitions {
                        add(PLACEHOLDER) {
                            nestedProp()
                        }
                    }
                    dependencies {
                        add(PLACEHOLDER) {
                            props {
                                nestedProp()
                            }
                            array(PLACEHOLDER)
                        }
                    }
                    description = PLACEHOLDER
                    enum(PLACEHOLDER)
                    example(PLACEHOLDER)
                    exclusiveMaximum()
                    exclusiveMinimum()
                    externalDocs {
                        description = PLACEHOLDER
                        url = PLACEHOLDER
                    }
                    format = PLACEHOLDER
                    id = PLACEHOLDER
                    items {
                        props {
                            nestedProp()
                        }
                        array(NESTED_PROP)
                    }
                    maxItems = 1
                    maxLength = 1
                    maxProperties = 1
                    maximum = 1.0
                    minItems = 1
                    minLength = 1
                    minProperties = 1
                    minimum = 1.0
                    multipleOf = 1.0
                    not {
                        nestedProp()
                    }
                    nullable()
                    oneOf {
                        addJSONSchemaProps { nestedProp() }
                    }
                    pattern = PLACEHOLDER
                    patternProperties {
                        add(PLACEHOLDER) {
                            nestedProp()
                        }
                    }
                    properties {
                        add(PLACEHOLDER) {
                            nestedProp()
                        }
                    }
                    required(PLACEHOLDER)
                    title = PLACEHOLDER
                    type = PLACEHOLDER
                    uniqueItems()
                    xKubernetesEmbeddedResource()
                    xKubernetesIntOrString()
                    xKubernetesListMapKeys(PLACEHOLDER)
                    xKubernetesListType = PLACEHOLDER
                    xKubernetesMapType = PLACEHOLDER
                    xKubernetesPreserveUnknownFields()
                    xKubernetesValidations {
                        addValidationRule {
                            rule = PLACEHOLDER
                            fieldPath = PLACEHOLDER
                            message = PLACEHOLDER
                            reason = PLACEHOLDER
                            optionalOldSelf()
                            messageExpression = PLACEHOLDER
                        }
                    }
                }
                expected = JSONSchemaProps(
                    ref = PLACEHOLDER,
                    schema = PLACEHOLDER,
                    additionalItems = JSONSchemaPropsOrBool(
                        props = NESTED_PROP,
                        bool = true
                    ),
                    allOf = listOf(NESTED_PROP),
                    anyOf = listOf(NESTED_PROP),
                    default = DEFAULT_JSON,
                    definitions = mapOf(PLACEHOLDER to NESTED_PROP),
                    dependencies = mapOf(PLACEHOLDER to JSON_SCHEMA_PROPS_OR_STRING_ARRAY),
                    description = PLACEHOLDER,
                    enum = listOf(DEFAULT_JSON),
                    example = DEFAULT_JSON,
                    exclusiveMaximum = true,
                    exclusiveMinimum = true,
                    externalDocs = ExternalDocumentation(
                        description = PLACEHOLDER,
                        url = PLACEHOLDER
                    ),
                    format = PLACEHOLDER,
                    id = PLACEHOLDER,
                    items = JSONSchemaPropsOrArray(
                        props = NESTED_PROP,
                        array = listOf(NESTED_PROP)
                    ),
                    maxItems = 1,
                    maxLength = 1,
                    maxProperties = 1,
                    maximum = 1.0,
                    minItems = 1,
                    minLength = 1,
                    minProperties = 1,
                    minimum = 1.0,
                    multipleOf = 1.0,
                    not = NESTED_PROP,
                    nullable = true,
                    oneOf = listOf(NESTED_PROP),
                    pattern = PLACEHOLDER,
                    patternProperties = mapOf(PLACEHOLDER to NESTED_PROP),
                    properties = mapOf(PLACEHOLDER to NESTED_PROP),
                    required = PLACEHOLDER_LIST,
                    title = PLACEHOLDER,
                    type = PLACEHOLDER,
                    uniqueItems = true,
                    xKubernetesEmbeddedResource = true,
                    xKubernetesIntOrString = true,
                    xKubernetesListMapKeys = PLACEHOLDER_LIST,
                    xKubernetesListType = PLACEHOLDER,
                    xKubernetesMapType = PLACEHOLDER,
                    xKubernetesPreserveUnknownFields = true,
                    xKubernetesValidations = listOf(
                        ValidationRule(
                            rule = PLACEHOLDER,
                            fieldPath = PLACEHOLDER,
                            message = PLACEHOLDER,
                            reason = PLACEHOLDER,
                            optionalOldSelf = true,
                            messageExpression = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}