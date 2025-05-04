package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectMetricSourceTest : FailureBuildSim<ObjectMetricSource, ObjectMetricSource.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectMetricSource, ObjectMetricSource.Builder> {
            requireScenario("describedObject") {
                given(ObjectMetricSource.Builder())
            }

            requireScenario("metric") {
                given(ObjectMetricSource.Builder()) {
                    describedObject {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }

            requireScenario("target") {
                given(ObjectMetricSource.Builder()) {
                    describedObject {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}