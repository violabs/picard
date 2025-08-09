package io.violabs.picard.v2.resources.extend.webhook.validating


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidatingWebhookConfigurationListTest :
    FullBuildSim<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidatingWebhookConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ValidatingWebhookConfigurationListDslBuilder()) {
                        items {
                            validatingWebhookConfiguration {}
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
            possibilities<ValidatingWebhookConfigurationList, ValidatingWebhookConfigurationListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(ValidatingWebhookConfigurationListDslBuilder())
                }
            }
    }
}