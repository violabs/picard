package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceColumnDefinitionTest :
    FailureBuildSim<CustomResourceColumnDefinition, CustomResourceColumnDefinition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceColumnDefinitionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceColumnDefinition, CustomResourceColumnDefinition.Builder> {
                requireScenario("jsonPath") {
                    given(CustomResourceColumnDefinition.Builder())
                }

                requireScenario("name") {
                    given(CustomResourceColumnDefinition.Builder()) {
                        jsonPath = PLACEHOLDER
                    }
                }

                requireScenario("type") {
                    given(CustomResourceColumnDefinition.Builder()) {
                        jsonPath = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }
    }
}