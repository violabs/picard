package io.violabs.picard.domain.k8sResources.extend.webhook


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WebhookConversionTest : FailureBuildSim<WebhookConversion, WebhookConversion.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            WebhookConversionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<WebhookConversion, WebhookConversion.Builder> {
            requireNotEmptyScenario("conversionReviewVersions") {
                given(WebhookConversion.Builder())
            }
        }
    }
}