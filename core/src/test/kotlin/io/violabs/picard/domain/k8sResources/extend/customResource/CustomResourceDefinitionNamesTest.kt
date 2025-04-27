package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionNamesTest :
    FailureBuildSim<CustomResourceDefinitionNames, CustomResourceDefinitionNames.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionNamesTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionNames, CustomResourceDefinitionNames.Builder> {
                requireScenario("kind") {
                    given(CustomResourceDefinitionNames.Builder())
                }

                requireScenario("plural") {
                    given(CustomResourceDefinitionNames.Builder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}