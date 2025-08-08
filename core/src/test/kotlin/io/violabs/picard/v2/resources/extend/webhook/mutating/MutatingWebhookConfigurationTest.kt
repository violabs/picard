package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.extend.webhook.MatchCondition
import io.violabs.picard.v2.resources.extend.webhook.RuleWithOperations
import io.violabs.picard.v2.resources.extend.webhook.ServiceReference
import io.violabs.picard.v2.resources.extend.webhook.WebhookClientConfig
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookConfigurationTest :
    SuccessBuildSim<MutatingWebhookConfiguration, MutatingWebhookConfigurationDslBuilder>() {
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
                service = ServiceReference(
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
            possibilities<MutatingWebhookConfiguration, MutatingWebhookConfigurationDslBuilder> {
                scenario {
                    id = "minimum"
                    given(MutatingWebhookConfigurationDslBuilder())
                    expected = MutatingWebhookConfiguration()
                }

                scenario {
                    id = "full"
                    given(MutatingWebhookConfigurationDslBuilder()) {
                        metadata { sharedObjectMeta() }
                        webhooks {
                            mutatingWebhook {
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
                                    matchCondition {
                                        expression = PLACEHOLDER
                                        name = PLACEHOLDER
                                    }
                                }
                                matchPolicy = PLACEHOLDER
                                namespaceSelector { sharedSelector() }
                                objectSelector { sharedSelector() }
                                reinvocationPolicy = PLACEHOLDER
                                rules {
                                    ruleWithOperations {
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