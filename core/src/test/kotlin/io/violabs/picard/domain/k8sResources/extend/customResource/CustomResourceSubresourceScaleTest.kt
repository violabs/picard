package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceSubresourceScaleTest :
    FailureBuildSim<CustomResourceSubresourceScale, CustomResourceSubresourceScale.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceSubresourceScaleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceSubresourceScale, CustomResourceSubresourceScale.Builder> {
                requireScenario("specReplicasPath") {
                    given(CustomResourceSubresourceScale.Builder())
                }

                requireScenario("statusReplicasPath") {
                    given(CustomResourceSubresourceScale.Builder()) {
                        specReplicasPath = PLACEHOLDER
                    }
                }
            }
    }
}