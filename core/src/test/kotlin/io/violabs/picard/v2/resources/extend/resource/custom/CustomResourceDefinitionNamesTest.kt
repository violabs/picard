package io.violabs.picard.v2.resources.extend.resource.custom


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionNamesTest :
    FailureBuildSim<CustomResourceDefinitionNames, CustomResourceDefinitionNamesDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionNamesTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionNames, CustomResourceDefinitionNamesDslBuilder> {
                requireScenario("kind") {
                    given(CustomResourceDefinitionNamesDslBuilder())
                }

                requireScenario("plural") {
                    given(CustomResourceDefinitionNamesDslBuilder()) {
                        kind = PLACEHOLDER
                    }
                }
            }
    }
}