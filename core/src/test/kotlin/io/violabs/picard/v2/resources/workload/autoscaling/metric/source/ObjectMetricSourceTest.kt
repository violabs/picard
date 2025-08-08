package io.violabs.picard.v2.resources.workload.autoscaling.metric.source


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectMetricSourceTest : FailureBuildSim<ObjectMetricSource, ObjectMetricSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectMetricSource, ObjectMetricSourceDslBuilder> {
            requireScenario("describedObject") {
                given(ObjectMetricSourceDslBuilder())
            }

            requireScenario("metric") {
                given(ObjectMetricSourceDslBuilder()) {
                    describedObject {
                        kind = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }

            requireScenario("target") {
                given(ObjectMetricSourceDslBuilder()) {
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