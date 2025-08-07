package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionVersionTest :
    FailureBuildSim<CustomResourceDefinitionVersion, CustomResourceDefinitionVersionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionVersionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionVersion, CustomResourceDefinitionVersionDslBuilder> {
                requireScenario("name") {
                    given(CustomResourceDefinitionVersionDslBuilder())
                }

                requireScenario("served") {
                    given(CustomResourceDefinitionVersionDslBuilder()) {
                        name = PLACEHOLDER
                    }
                }

                requireScenario("storage") {
                    given(CustomResourceDefinitionVersionDslBuilder()) {
                        name = PLACEHOLDER
                        served()
                    }
                }
            }
    }
}