package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectMetricStatusTest : FailureBuildSim<ObjectMetricStatus, ObjectMetricStatus.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectMetricStatus, ObjectMetricStatus.Builder> {
            requireScenario("current") {
                given(ObjectMetricStatus.Builder())
            }

            requireScenario("describedObject") {
                given(ObjectMetricStatus.Builder()) {
                    current {

                    }
                }
            }

            requireScenario("metric") {
                given(ObjectMetricStatus.Builder()) {
                    current {

                    }
                    describedObject {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }

            requireScenario("pods") {
                given(ObjectMetricStatus.Builder()) {
                    current {

                    }
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