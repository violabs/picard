package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookConfigurationListTest :
    FullBuildSim<MutatingWebhookConfigurationList, MutatingWebhookConfigurationList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingWebhookConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<MutatingWebhookConfigurationList, MutatingWebhookConfigurationList.Builder> {
                scenario {
                    id = "minimum"
                    given(MutatingWebhookConfigurationList.Builder()) {
                        items {
                            config {}
                        }
                    }
                    expected = MutatingWebhookConfigurationList(
                        items = listOf(MutatingWebhookConfiguration())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<MutatingWebhookConfigurationList, MutatingWebhookConfigurationList.Builder> {
                requireNotEmptyScenario("items") {
                    given(MutatingWebhookConfigurationList.Builder())
                }
            }
    }
}