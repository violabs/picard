package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MutatingWebhookConfigurationListTest :
    FullBuildSim<MutatingWebhookConfigurationList, MutatingWebhookConfigurationListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MutatingWebhookConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<MutatingWebhookConfigurationList, MutatingWebhookConfigurationListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(MutatingWebhookConfigurationListDslBuilder()) {
                        items {
                            mutatingWebhookConfiguration {}
                        }
                    }
                    expected = MutatingWebhookConfigurationList(
                        items = listOf(MutatingWebhookConfiguration())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<MutatingWebhookConfigurationList, MutatingWebhookConfigurationListDslBuilder> {
//                requireNotEmptyScenario("items") {
                requireScenario("items") {
                    given(MutatingWebhookConfigurationListDslBuilder())
                }
            }
    }
}