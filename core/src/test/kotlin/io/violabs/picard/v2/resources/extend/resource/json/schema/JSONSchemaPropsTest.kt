//package io.violabs.picard.v2.resources.extend.resource.json.schema
//
//
//import io.violabs.picard.SuccessBuildSim
//import io.violabs.picard.possibilities
//import org.junit.jupiter.api.BeforeAll
//
//class JSONSchemaPropsTest : SuccessBuildSim<JsonSchemaProps, JsonSchemaPropsDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            JSONSchemaPropsTest::class,
//            SUCCESS_POSSIBILITIES
//        )
//
//        private val NESTED_PROP = JsonSchemaProps(
//            ref = PLACEHOLDER,
//            schema = PLACEHOLDER
//        )
//        private val JSON_SCHEMA_PROPS_OR_STRING_ARRAY = JsonSchemaPropsOrStringArray(
//            props = NESTED_PROP,
//            array = listOf(PLACEHOLDER)
//        )
//        private val DEFAULT_JSON = J(PLACEHOLDER)
//
//        private fun JsonSchemaPropsDslBuilder.nestedProp() {
//            ref = PLACEHOLDER
//            schema = PLACEHOLDER
//        }
//
//        private val SUCCESS_POSSIBILITIES = possibilities<JsonSchemaProps, JsonSchemaPropsDslBuilder> {
//            scenario {
//                id = "full"
//                given(JsonSchemaPropsDslBuilder()) {
//                    ref = PLACEHOLDER
//                    schema = PLACEHOLDER
//                    additionalItems {
//                        schema { nestedProp() }
//                        allows()
//                    }
//                    allOf {
//                        this.
//                    }
//                    anyOf {
//                        addJSONSchemaProps { nestedProp() }
//                    }
//                    default(PLACEHOLDER)
//                    definitions {
//                        add(PLACEHOLDER) {
//                            nestedProp()
//                        }
//                    }
//                    dependencies {
//                        add(PLACEHOLDER) {
//                            props {
//                                nestedProp()
//                            }
//                            array(PLACEHOLDER)
//                        }
//                    }
//                    description = PLACEHOLDER
//                    enum(PLACEHOLDER)
//                    example(PLACEHOLDER)
//                    exclusiveMaximum()
//                    exclusiveMinimum()
//                    externalDocs {
//                        description = PLACEHOLDER
//                        url = PLACEHOLDER
//                    }
//                    format = PLACEHOLDER
//                    id = PLACEHOLDER
//                    items {
//                        props {
//                            nestedProp()
//                        }
//                        array(NESTED_PROP)
//                    }
//                    maxItems = 1
//                    maxLength = 1
//                    maxProperties = 1
//                    maximum = 1.0
//                    minItems = 1
//                    minLength = 1
//                    minProperties = 1
//                    minimum = 1.0
//                    multipleOf = 1.0
//                    not {
//                        nestedProp()
//                    }
//                    nullable()
//                    oneOf {
//                        addJSONSchemaProps { nestedProp() }
//                    }
//                    pattern = PLACEHOLDER
//                    patternProperties {
//                        add(PLACEHOLDER) {
//                            nestedProp()
//                        }
//                    }
//                    properties {
//                        add(PLACEHOLDER) {
//                            nestedProp()
//                        }
//                    }
//                    required(PLACEHOLDER)
//                    title = PLACEHOLDER
//                    type = PLACEHOLDER
//                    uniqueItems()
//                    xKubernetesEmbeddedResource()
//                    xKubernetesIntOrString()
//                    xKubernetesListMapKeys(PLACEHOLDER)
//                    xKubernetesListType = PLACEHOLDER
//                    xKubernetesMapType = PLACEHOLDER
//                    xKubernetesPreserveUnknownFields()
//                    xKubernetesValidations {
//                        addValidationRule {
//                            rule = PLACEHOLDER
//                            fieldPath = PLACEHOLDER
//                            message = PLACEHOLDER
//                            reason = PLACEHOLDER
//                            optionalOldSelf()
//                            messageExpression = PLACEHOLDER
//                        }
//                    }
//                }
//                expected = JSONSchemaProps(
//                    ref = PLACEHOLDER,
//                    schema = PLACEHOLDER,
//                    additionalItems = JSONSchemaPropsOrBool(
//                        props = NESTED_PROP,
//                        bool = true
//                    ),
//                    allOf = listOf(NESTED_PROP),
//                    anyOf = listOf(NESTED_PROP),
//                    default = DEFAULT_JSON,
//                    definitions = mapOf(PLACEHOLDER to NESTED_PROP),
//                    dependencies = mapOf(PLACEHOLDER to JSON_SCHEMA_PROPS_OR_STRING_ARRAY),
//                    description = PLACEHOLDER,
//                    enum = listOf(DEFAULT_JSON),
//                    example = DEFAULT_JSON,
//                    exclusiveMaximum = true,
//                    exclusiveMinimum = true,
//                    externalDocs = ExternalDocumentation(
//                        description = PLACEHOLDER,
//                        url = PLACEHOLDER
//                    ),
//                    format = PLACEHOLDER,
//                    id = PLACEHOLDER,
//                    items = JSONSchemaPropsOrArray(
//                        props = NESTED_PROP,
//                        array = listOf(NESTED_PROP)
//                    ),
//                    maxItems = 1,
//                    maxLength = 1,
//                    maxProperties = 1,
//                    maximum = 1.0,
//                    minItems = 1,
//                    minLength = 1,
//                    minProperties = 1,
//                    minimum = 1.0,
//                    multipleOf = 1.0,
//                    not = NESTED_PROP,
//                    nullable = true,
//                    oneOf = listOf(NESTED_PROP),
//                    pattern = PLACEHOLDER,
//                    patternProperties = mapOf(PLACEHOLDER to NESTED_PROP),
//                    properties = mapOf(PLACEHOLDER to NESTED_PROP),
//                    required = PLACEHOLDER_LIST,
//                    title = PLACEHOLDER,
//                    type = PLACEHOLDER,
//                    uniqueItems = true,
//                    xKubernetesEmbeddedResource = true,
//                    xKubernetesIntOrString = true,
//                    xKubernetesListMapKeys = PLACEHOLDER_LIST,
//                    xKubernetesListType = PLACEHOLDER,
//                    xKubernetesMapType = PLACEHOLDER,
//                    xKubernetesPreserveUnknownFields = true,
//                    xKubernetesValidations = listOf(
//                        ValidationRule(
//                            rule = PLACEHOLDER,
//                            fieldPath = PLACEHOLDER,
//                            message = PLACEHOLDER,
//                            reason = PLACEHOLDER,
//                            optionalOldSelf = true,
//                            messageExpression = PLACEHOLDER
//                        )
//                    )
//                )
//            }
//        }
//    }
//}