package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingWebhookConfigurationListTest :
    FullBuildSim<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingWebhookConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationList.Builder> {
                scenario {
                    id = "minimum"
                    given(ValidatingWebhookConfigurationList.Builder()) {
                        items {
                            config {}
                        }
                    }
                    expected = ValidatingWebhookConfigurationList(
                        items = listOf(
                            ValidatingWebhookConfiguration()
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationList.Builder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingWebhookConfigurationList.Builder())
                }
            }
    }
}