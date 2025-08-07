package io.violabs.picard.domain.k8sResources.workload.metric


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectMetricStatusTest : FailureBuildSim<ObjectMetricStatus, ObjectMetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectMetricStatus, ObjectMetricStatusDslBuilder> {
            requireScenario("current") {
                given(ObjectMetricStatusDslBuilder())
            }

            requireScenario("describedObject") {
                given(ObjectMetricStatusDslBuilder()) {
                    current {

                    }
                }
            }

            requireScenario("metric") {
                given(ObjectMetricStatusDslBuilder()) {
                    current {

                    }
                    describedObject {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }

            requireScenario("pods") {
                given(ObjectMetricStatusDslBuilder()) {
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