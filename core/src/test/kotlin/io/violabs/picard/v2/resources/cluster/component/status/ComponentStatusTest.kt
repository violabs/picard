package io.violabs.picard.v2.resources.cluster.component.status

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentStatusTest : SuccessBuildSim<ComponentStatus, ComponentStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentStatusTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ComponentStatus, ComponentStatusDslBuilder> {
            scenario {
                id = "minimum"
                given(ComponentStatusDslBuilder())
                expected = ComponentStatus()
            }

            scenario {
                id = "full"
                given(ComponentStatusDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    conditions {
                        componentCondition {
                            status = "True"
                            type = PLACEHOLDER
                            errorValue = PLACEHOLDER
                            message = PLACEHOLDER
                        }
                    }
                }
                expected = ComponentStatus(
                    metadata = OBJECT_META,
                    conditions = listOf(
                        ComponentCondition(
                            status = "True",
                            type = PLACEHOLDER,
                            errorValue = PLACEHOLDER,
                            message = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}