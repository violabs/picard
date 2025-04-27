package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfigServiceReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookConfigurationTest :
    SuccessBuildSim<MutatingWebhookConfiguration, MutatingWebhookConfiguration.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingWebhookConfigurationTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val MUTATING_WEBHOOK = MutatingWebhook(
            admissionReviewVersions = PLACEHOLDER_LIST,
            clientConfig = WebhookClientConfig(
                caBundle = BYTES,
                service = WebhookClientConfigServiceReference(
                    name = PLACEHOLDER,
                    namespace = PLACEHOLDER,
                    port = PORT_NUMBER
                ),
                url = PLACEHOLDER
            ),
            name = PLACEHOLDER,
            sideEffects = PLACEHOLDER,
            failurePolicy = PLACEHOLDER,
            matchConditions = listOf(
                MatchCondition(
                    expression = PLACEHOLDER,
                    name = PLACEHOLDER
                )
            ),
            matchPolicy = PLACEHOLDER,
            namespaceSelector = LABEL_SELECTOR,
            objectSelector = LABEL_SELECTOR,
            reinvocationPolicy = PLACEHOLDER,
            rules = listOf(
                RuleWithOperations(
                    apiGroups = PLACEHOLDER_LIST,
                    apiVersions = PLACEHOLDER_LIST,
                    operations = PLACEHOLDER_LIST,
                    resources = PLACEHOLDER_LIST,
                    scope = PLACEHOLDER
                )
            ),
            timeoutSeconds = 1
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<MutatingWebhookConfiguration, MutatingWebhookConfiguration.Builder> {
                scenario {
                    id = "minimum"
                    given(MutatingWebhookConfiguration.Builder())
                    expected = MutatingWebhookConfiguration()
                }

                scenario {
                    id = "full"
                    given(MutatingWebhookConfiguration.Builder()) {
                        sharedMetadata()
                        webhooks {
                            webhook {
                                admissionReviewVersions(PLACEHOLDER)
                                clientConfig {
                                    caBundle(BYTE_1, BYTE_2)
                                    service {
                                        name = PLACEHOLDER
                                        namespace = PLACEHOLDER
                                        port = PORT_NUMBER
                                    }
                                    url = PLACEHOLDER
                                }
                                name = PLACEHOLDER
                                sideEffects = PLACEHOLDER
                                failurePolicy = PLACEHOLDER
                                matchConditions {
                                    condition {
                                        expression = PLACEHOLDER
                                        name = PLACEHOLDER
                                    }
                                }
                                matchPolicy = PLACEHOLDER
                                namespaceSelector { sharedSelector() }
                                objectSelector { sharedSelector() }
                                reinvocationPolicy = PLACEHOLDER
                                rules {
                                    rule {
                                        apiGroups(PLACEHOLDER)
                                        apiVersions(PLACEHOLDER)
                                        operations(PLACEHOLDER)
                                        resources(PLACEHOLDER)
                                        scope = PLACEHOLDER
                                    }
                                }
                                timeoutSeconds = 1
                            }
                        }
                    }
                    expected = MutatingWebhookConfiguration(
                        metadata = METADATA,
                        webhooks = listOf(MUTATING_WEBHOOK)
                    )
                }
            }
    }
}