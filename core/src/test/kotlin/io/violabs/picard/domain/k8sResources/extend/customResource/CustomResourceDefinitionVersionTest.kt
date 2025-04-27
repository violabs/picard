package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionVersionTest :
    FailureBuildSim<CustomResourceDefinitionVersion, CustomResourceDefinitionVersion.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionVersionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionVersion, CustomResourceDefinitionVersion.Builder> {
                requireScenario("name") {
                    given(CustomResourceDefinitionVersion.Builder())
                }

                requireScenario("served") {
                    given(CustomResourceDefinitionVersion.Builder()) {
                        name = PLACEHOLDER
                    }
                }

                requireScenario("storage") {
                    given(CustomResourceDefinitionVersion.Builder()) {
                        name = PLACEHOLDER
                        served()
                    }
                }
            }
    }
}