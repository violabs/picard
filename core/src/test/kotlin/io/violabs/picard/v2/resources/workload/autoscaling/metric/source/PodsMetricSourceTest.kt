package io.violabs.picard.v2.resources.workload.autoscaling.metric.source

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodsMetricSourceTest : FailureBuildSim<PodsMetricSource, PodsMetricSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodsMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodsMetricSource, PodsMetricSourceDslBuilder> {
            requireScenario("metric") {
                given(PodsMetricSourceDslBuilder())
            }

            requireScenario("target") {
                given(PodsMetricSourceDslBuilder()) {
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}