package io.violabs.picard.domain.k8sResources.cluster.componentStatus


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.condition.ComponentCondition
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentStatusTest : SuccessBuildSim<ComponentStatus, ComponentStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentStatusTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ComponentStatus, ComponentStatus.Builder> {
            scenario {
                id = "minimum"
                given(ComponentStatus.Builder())
                expected = ComponentStatus()
            }

            scenario {
                id = "full"
                given(ComponentStatus.Builder()) {
                    sharedMetadata()
                    conditions {
                        condition {
                            status = BooleanType.True
                            type = PLACEHOLDER
                            message = PLACEHOLDER
                            reason = PLACEHOLDER
                        }
                    }
                }
                expected = ComponentStatus(
                    metadata = METADATA,
                    conditions = listOf(
                        ComponentCondition(
                            status = BooleanType.True,
                            type = PLACEHOLDER,
                            message = PLACEHOLDER,
                            reason = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}