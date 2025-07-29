package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.extend.resource.json.schema.JsonSchemaProps
import io.violabs.picard.v2.resources.extend.resource.webhook.WebhookClientConfig
import io.violabs.picard.v2.resources.extend.resource.webhook.ServiceReference
import io.violabs.picard.v2.resources.extend.resource.webhook.WebhookConversion
import org.junit.jupiter.api.BeforeAll
import java.time.ZoneOffset

class CustomResourceDefinitionTest : SuccessBuildSim<CustomResourceDefinitionV2, CustomResourceDefinitionV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionTest::class,
            SUCCESS_POSSIBILITIES
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
            schema = CustomResourceValidation(JsonSchemaProps()),
            selectableFields = listOf(SelectableField(PLACEHOLDER)),
            subresources = CustomResourceSubresources(
                status = CustomResourceSubresourceStatus.All,
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
                conversionReviewVersions = listOf(PLACEHOLDER),
                clientConfig = WebhookClientConfig(
                    service = ServiceReference(
                        name = PLACEHOLDER,
                        namespace = PLACEHOLDER,
                        port = 443
                    ),
                    caBundle = listOf(1.toByte(), 2.toByte()),
                    url = PLACEHOLDER
                )
            )
        )

        private val NAMES = CustomResourceDefinitionNames(
            kind = PLACEHOLDER,
            plural = PLACEHOLDER,
            categories = listOf(PLACEHOLDER),
            listKind = PLACEHOLDER,
            shortNames = listOf(PLACEHOLDER),
            singular = PLACEHOLDER
        )

        private val SPEC = CustomResourceDefinitionSpec(
            group = PLACEHOLDER,
            names = NAMES,
            scope = PLACEHOLDER,
            versions = listOf(DEFINITION_VERSION),
            conversion = CONVERSION,
            preserveUnknownFields = true
        )

        private val STATUS = CustomResourceDefinitionStatus(
            acceptedNames = NAMES,
            conditions = listOf(
                CustomResourceDefinitionCondition(
                    lastTransitionTime = NOW.toInstant(ZoneOffset.UTC),
                    message = PLACEHOLDER,
                    reason = PLACEHOLDER,
                    status = PLACEHOLDER,
                    type = PLACEHOLDER
                )
            ),
            storedVersions = listOf(PLACEHOLDER)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CustomResourceDefinitionV2, CustomResourceDefinitionV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CustomResourceDefinitionV2DslBuilder()) {
                    spec {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                        }
                        scope = PLACEHOLDER
                        versions {
                            customResourceDefinitionVersion {
                                name = PLACEHOLDER
                                served(true)
                                storage(true)
                            }
                        }
                    }
                }
                expected = CustomResourceDefinitionV2(
                    spec = CustomResourceDefinitionSpec(
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
                given(CustomResourceDefinitionV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
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
                            customResourceDefinitionVersion {
                                name = PLACEHOLDER
                                served(true)
                                storage(true)
                                additionalPrinterColumns {
                                    customResourceColumnDefinition {
                                        jsonPath = PLACEHOLDER
                                        name = PLACEHOLDER
                                        type = PLACEHOLDER
                                        description = PLACEHOLDER
                                        format = PLACEHOLDER
                                        priority = 1
                                    }
                                }
                                deprecated(true)
                                deprecationWarning = PLACEHOLDER
                                schema {
                                    openApiV3Schema {

                                    }
                                }
                                selectableFields {
                                    selectableField {
                                        jsonPath = PLACEHOLDER
                                    }
                                }
                                subresources {
                                    status = CustomResourceSubresourceStatus.All
                                    scale {
                                        specReplicasPath = PLACEHOLDER
                                        statusReplicasPath = PLACEHOLDER
                                        labelSelectorPath = PLACEHOLDER
                                    }
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
                                        port = 443
                                    }
                                    caBundle(1.toByte(), 2.toByte())
                                    url = PLACEHOLDER
                                }
                            }
                        }
                        preserveUnknownFields(true)
                    }
                    status {
                        acceptedNames {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                            categories(PLACEHOLDER)
                            listKind = PLACEHOLDER
                            shortNames(PLACEHOLDER)
                            singular = PLACEHOLDER
                        }
                        conditions {
                            customResourceDefinitionCondition {
                                lastTransitionTime = NOW.toInstant(ZoneOffset.UTC)
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                status = PLACEHOLDER
                                type = PLACEHOLDER
                            }
                        }
                        storedVersions(PLACEHOLDER)
                    }
                }
                expected = CustomResourceDefinitionV2(
                    metadata = Common.OBJECT_META,
                    spec = SPEC,
                    status = STATUS
                )
            }
        }
    }
}