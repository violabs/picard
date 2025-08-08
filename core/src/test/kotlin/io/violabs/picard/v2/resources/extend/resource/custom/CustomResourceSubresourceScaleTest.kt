package io.violabs.picard.v2.resources.extend.resource.custom


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceSubresourceScaleTest :
    FailureBuildSim<CustomResourceSubresourceScale, CustomResourceSubresourceScaleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceSubresourceScaleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceSubresourceScale, CustomResourceSubresourceScaleDslBuilder> {
                requireScenario("specReplicasPath") {
                    given(CustomResourceSubresourceScaleDslBuilder())
                }

                requireScenario("statusReplicasPath") {
                    given(CustomResourceSubresourceScaleDslBuilder()) {
                        specReplicasPath = PLACEHOLDER
                    }
                }
            }
    }
}