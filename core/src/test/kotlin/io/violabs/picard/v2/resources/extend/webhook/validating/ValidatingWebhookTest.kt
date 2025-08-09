package io.violabs.picard.v2.resources.extend.webhook.validating


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingWebhookTest : FailureBuildSim<ValidatingWebhook, ValidatingWebhookDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingWebhookTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ValidatingWebhook, ValidatingWebhookDslBuilder> {
            requireNotEmptyScenario("admissionReviewVersions") {
                given(ValidatingWebhookDslBuilder())
            }


            requireScenario("clientConfig") {
                given(ValidatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                }
            }

            requireScenario("name") {
                given(ValidatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                }
            }


            requireScenario("sideEffects") {
                given(ValidatingWebhookDslBuilder()) {
                    admissionReviewVersions(PLACEHOLDER)
                    clientConfig {}
                    name = PLACEHOLDER
                }
            }
        }
    }
}