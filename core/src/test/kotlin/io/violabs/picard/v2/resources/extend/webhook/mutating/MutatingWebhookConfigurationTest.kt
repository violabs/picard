package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.picard.Common
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
                        metadata {
                            sharedObjectMeta()
                        }
                        webhooks {
                            mutatingWebhook {
                                admissionReviewVersions(PLACEHOLDER)
                                clientConfig {
                                    caBundle(1.toByte())
                                    service {
                                        name = PLACEHOLDER
                                        namespace = PLACEHOLDER
                                        port = PORT_NUMBER
                                        path = PLACEHOLDER
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
                        metadata = Common.OBJECT_META,
                        webhooks = listOf(
                            MutatingWebhook(
                                admissionReviewVersions = listOf(PLACEHOLDER),
                                clientConfig = WebhookClientConfig(
                                    caBundle = listOf(1.toByte()),
                                    service = ServiceReference(
                                        name = PLACEHOLDER,
                                        namespace = PLACEHOLDER,
                                        port = PORT_NUMBER,
                                        path = PLACEHOLDER
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
                                namespaceSelector = Common.LABEL_SELECTOR,
                                objectSelector = Common.LABEL_SELECTOR,
                                reinvocationPolicy = PLACEHOLDER,
                                rules = listOf(
                                    RuleWithOperations(
                                        apiGroups = listOf(PLACEHOLDER),
                                        apiVersions = listOf(PLACEHOLDER),
                                        operations = listOf(PLACEHOLDER),
                                        resources = listOf(PLACEHOLDER),
                                        scope = PLACEHOLDER
                                    )
                                ),
                                timeoutSeconds = 1
                            )
                        )
                    )
                }
            }
    }
}