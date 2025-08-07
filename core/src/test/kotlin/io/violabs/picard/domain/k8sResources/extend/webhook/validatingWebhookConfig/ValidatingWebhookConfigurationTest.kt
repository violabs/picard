package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfigServiceReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingWebhookConfigurationTest :
    SuccessBuildSim<ValidatingWebhookConfiguration, ValidatingWebhookConfigurationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingWebhookConfigurationTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val VALIDATING_WEBHOOK = ValidatingWebhook(
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
            possibilities<ValidatingWebhookConfiguration, ValidatingWebhookConfigurationDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingWebhookConfigurationDslBuilder())
                    expected = ValidatingWebhookConfiguration()
                }

                scenario {
                    id = "full"
                    given(ValidatingWebhookConfigurationDslBuilder()) {
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
                                    addMatchCondition {
                                        expression = PLACEHOLDER
                                        name = PLACEHOLDER
                                    }
                                }
                                matchPolicy = PLACEHOLDER
                                namespaceSelector { sharedSelector() }
                                objectSelector { sharedSelector() }
                                rules {
                                    addRuleWithOperations {
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
                        expected = ValidatingWebhookConfiguration(
                            metadata = METADATA,
                            webhooks = listOf(VALIDATING_WEBHOOK)
                        )
                    }
                }
            }
    }
}