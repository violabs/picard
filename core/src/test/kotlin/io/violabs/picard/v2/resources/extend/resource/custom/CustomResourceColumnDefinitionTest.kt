package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceColumnDefinitionTest :
    FailureBuildSim<CustomResourceColumnDefinition, CustomResourceColumnDefinitionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceColumnDefinitionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceColumnDefinition, CustomResourceColumnDefinitionDslBuilder> {
                requireScenario("jsonPath") {
                    given(CustomResourceColumnDefinitionDslBuilder())
                }

                requireScenario("name") {
                    given(CustomResourceColumnDefinitionDslBuilder()) {
                        jsonPath = PLACEHOLDER
                    }
                }

                requireScenario("type") {
                    given(CustomResourceColumnDefinitionDslBuilder()) {
                        jsonPath = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }
    }
}