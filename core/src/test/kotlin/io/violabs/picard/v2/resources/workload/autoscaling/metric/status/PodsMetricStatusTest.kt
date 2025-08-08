package io.violabs.picard.v2.resources.workload.autoscaling.metric.status


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodsMetricStatusTest : FailureBuildSim<PodsMetricStatus, PodsMetricStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodsMetricStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodsMetricStatus, PodsMetricStatusDslBuilder> {
            requireScenario("current") {
                given(PodsMetricStatusDslBuilder())
            }

            requireScenario("metric") {
                given(PodsMetricStatusDslBuilder()) {
                    current {

                    }
                }
            }

            requireScenario("resource") {
                given(PodsMetricStatusDslBuilder()) {
                    current {

                    }
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}