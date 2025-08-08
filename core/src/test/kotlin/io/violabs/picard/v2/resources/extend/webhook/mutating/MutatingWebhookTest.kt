package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookTest : FailureBuildSim<MutatingWebhook, MutatingWebhookDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingWebhookTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MutatingWebhook, MutatingWebhookDslBuilder> {
            requireScenario("admissionReviewVersions") {
                given(MutatingWebhookDslBuilder())
            }

            requireScenario("clientConfig") {
                given(MutatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                }
            }

            requireScenario("name") {
                given(MutatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                }
            }


            requireScenario("sideEffects") {
                given(MutatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                    name = PLACEHOLDER
                }
            }
        }
    }
}