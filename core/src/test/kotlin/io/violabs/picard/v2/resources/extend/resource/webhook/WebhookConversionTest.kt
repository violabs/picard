package io.violabs.picard.v2.resources.extend.resource.webhook

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WebhookConversionTest : FailureBuildSim<WebhookConversion, WebhookConversionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            WebhookConversionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<WebhookConversion, WebhookConversionDslBuilder> {
            requireNotEmptyScenario("conversionReviewVersions") {
                given(WebhookConversionDslBuilder())
            }
        }
    }
}