package io.violabs.picard.v2.resources.extend.resource.custom


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionSpecTest :
    FailureBuildSim<CustomResourceDefinitionSpec, CustomResourceDefinitionSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinitionSpec, CustomResourceDefinitionSpecDslBuilder> {
                requireScenario("group") {
                    given(CustomResourceDefinitionSpecDslBuilder())
                }

                requireScenario("names") {
                    given(CustomResourceDefinitionSpecDslBuilder()) {
                        group = PLACEHOLDER
                    }
                }

                requireScenario("scope") {
                    given(CustomResourceDefinitionSpecDslBuilder()) {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                        }
                    }
                }

                requireScenario("versions") {
                    given(CustomResourceDefinitionSpecDslBuilder()) {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                        }
                        scope = PLACEHOLDER
                    }
                }
            }
    }
}