package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.extend.customResource.*
import io.violabs.picard.domain.k8sResources.extend.json.JSONSchemaProps
import io.violabs.picard.domain.k8sResources.extend.json.SelectableField
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfigServiceReference
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookConversion
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionTest : FullBuildSim<CustomResourceDefinition, CustomResourceDefinition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val COLUMN_DEFINITION = CustomResourceColumnDefinition(
            jsonPath = PLACEHOLDER,
            name = PLACEHOLDER,
            type = PLACEHOLDER,
            description = PLACEHOLDER,
            format = PLACEHOLDER,
            priority = 1
        )

        private val DEFINITION_VERSION = CustomResourceDefinitionVersion(
            name = PLACEHOLDER,
            served = true,
            storage = true,
            additionalPrinterColumns = listOf(COLUMN_DEFINITION),
            deprecated = true,
            deprecationWarning = PLACEHOLDER,
            schema = CustomResourceValidation(openAPIV3Schema = JSONSchemaProps()),
            selectableFields = listOf(SelectableField(PLACEHOLDER)),
            subresources = CustomResourceSubresources(
                status = CustomResourceSubresourceStatus(PLACEHOLDER),
                scale = CustomResourceSubresourceScale(
                    specReplicasPath = PLACEHOLDER,
                    statusReplicasPath = PLACEHOLDER,
                    labelSelectorPath = PLACEHOLDER
                )
            )
        )

        private val CONVERSION = CustomResourceConversion(
            strategy = PLACEHOLDER,
            webhook = WebhookConversion(
                conversionReviewVersions = PLACEHOLDER_LIST,
                clientConfig = WebhookClientConfig(
                    service = WebhookClientConfigServiceReference(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER,
                        port = PORT_NUMBER
                    ),
                    caBundle = BYTES,
                    url = PLACEHOLDER
                )
            )
        )

        private val NAMES = CustomResourceDefinitionNames(
            kind = PLACEHOLDER,
            plural = PLACEHOLDER,
            categories = PLACEHOLDER_LIST,
            listKind = PLACEHOLDER,
            shortNames = PLACEHOLDER_LIST,
            singular = PLACEHOLDER
        )

        private val SPEC = CustomResourceDefinition.Spec(
            group = PLACEHOLDER,
            names = NAMES,
            scope = PLACEHOLDER,
            versions = listOf(DEFINITION_VERSION),
            conversion = CONVERSION,
            preserveUnknownFields = true
        )

        private val STATUS = CustomResourceDefinition.Status(
            acceptedNames = NAMES,
            conditions = listOf(CONDITION),
            storedVersions = PLACEHOLDER_LIST
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CustomResourceDefinition, CustomResourceDefinition.Builder> {
            scenario {
                id = "minimum"
                given(CustomResourceDefinition.Builder()) {
                    spec {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                        }
                        scope = PLACEHOLDER
                        versions {
                            name = PLACEHOLDER
                            served()
                            storage()
                        }
                    }
                }
                expected = CustomResourceDefinition(
                    spec = CustomResourceDefinition.Spec(
                        group = PLACEHOLDER,
                        names = CustomResourceDefinitionNames(
                            kind = PLACEHOLDER,
                            plural = PLACEHOLDER
                        ),
                        scope = PLACEHOLDER,
                        versions = listOf(
                            CustomResourceDefinitionVersion(
                                name = PLACEHOLDER,
                                served = true,
                                storage = true
                            )
                        ),
                    )
                )
            }

            scenario {
                id = "full"
                given(CustomResourceDefinition.Builder()) {
                    spec {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                            categories(PLACEHOLDER)
                            listKind = PLACEHOLDER
                            shortNames(PLACEHOLDER)
                            singular = PLACEHOLDER
                        }
                        scope = PLACEHOLDER
                        versions {
                            name = PLACEHOLDER
                            served()
                            storage()
                            additionalPrinterColumns {
                                definition {
                                    jsonPath = PLACEHOLDER
                                    name = PLACEHOLDER
                                    type = PLACEHOLDER
                                    description = PLACEHOLDER
                                    format = PLACEHOLDER
                                    priority = 1
                                }
                            }
                            deprecated()
                            deprecationWarning = PLACEHOLDER
                            schema {
                                openAPIV3Schema {

                                }
                            }
                            selectableFields {
                                field {
                                    jsonPath = PLACEHOLDER
                                }
                            }
                            subresources {
                                status {
                                    content = PLACEHOLDER
                                }
                                scale {
                                    specReplicasPath = PLACEHOLDER
                                    statusReplicasPath = PLACEHOLDER
                                    labelSelectorPath = PLACEHOLDER
                                }
                            }
                        }
                        conversion {
                            strategy = PLACEHOLDER
                            webhook {
                                conversionReviewVersions(PLACEHOLDER)
                                clientConfig {
                                    service {
                                        name = PLACEHOLDER
                                        namespace = PLACEHOLDER
                                        port = PORT_NUMBER
                                    }
                                    caBundle(BYTE_1, BYTE_2)
                                    url = PLACEHOLDER
                                }
                            }
                        }
                        preserveUnknownFields()
                    }

                    this.status {
                        acceptedNames {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                            categories(PLACEHOLDER)
                            listKind = PLACEHOLDER
                            shortNames(PLACEHOLDER)
                            singular = PLACEHOLDER
                        }
                        conditions {
                            sharedCondition()
                        }
                        storedVersions(PLACEHOLDER)
                    }

                    sharedMetadata()
                }
                expected = CustomResourceDefinition(
                    spec = SPEC,
                    status = STATUS,
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CustomResourceDefinition, CustomResourceDefinition.Builder> {
            requireScenario("spec") {
                given(CustomResourceDefinition.Builder())
            }
        }
    }
}