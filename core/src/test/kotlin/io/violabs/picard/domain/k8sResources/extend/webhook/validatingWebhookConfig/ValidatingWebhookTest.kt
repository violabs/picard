package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingWebhookTest : FailureBuildSim<ValidatingWebhook, ValidatingWebhook.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingWebhookTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ValidatingWebhook, ValidatingWebhook.Builder> {
            requireScenario("admissionReviewVersions") {
                given(ValidatingWebhook.Builder())
            }


            requireScenario("clientConfig") {
                given(ValidatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                }
            }

            requireScenario("name") {
                given(ValidatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                }
            }


            requireScenario("sideEffects") {
                given(ValidatingWebhook.Builder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                    name = PLACEHOLDER
                }
            }
        }
    }
}