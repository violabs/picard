package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceDefinitionSpecTest :
    FailureBuildSim<CustomResourceDefinition.Spec, CustomResourceDefinition.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceDefinitionSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<CustomResourceDefinition.Spec, CustomResourceDefinition.Spec.Builder> {
                requireScenario("group") {
                    given(CustomResourceDefinition.Spec.Builder())
                }

                requireScenario("names") {
                    given(CustomResourceDefinition.Spec.Builder()) {
                        group = PLACEHOLDER
                    }
                }

                requireScenario("scope") {
                    given(CustomResourceDefinition.Spec.Builder()) {
                        group = PLACEHOLDER
                        names {
                            kind = PLACEHOLDER
                            plural = PLACEHOLDER
                        }
                    }
                }

                requireScenario("versions") {
                    given(CustomResourceDefinition.Spec.Builder()) {
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