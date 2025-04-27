package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookTest : FailureBuildSim<MutatingWebhook, MutatingWebhook.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingWebhookTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MutatingWebhook, MutatingWebhook.Builder> {
            requireScenario("admissionReviewVersions") {
                given(MutatingWebhook.Builder())
            }

            requireScenario("clientConfig") {
                given(MutatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                }
            }

            requireScenario("name") {
                given(MutatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                }
            }


            requireScenario("sideEffects") {
                given(MutatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                    name = PLACEHOLDER
                }
            }
        }
    }
}