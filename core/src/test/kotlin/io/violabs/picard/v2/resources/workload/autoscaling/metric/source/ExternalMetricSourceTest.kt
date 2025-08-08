package io.violabs.picard.v2.resources.workload.autoscaling.metric.source


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExternalMetricSourceTest : FailureBuildSim<ExternalMetricSource, ExternalMetricSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExternalMetricSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExternalMetricSource, ExternalMetricSourceDslBuilder> {
            requireScenario("metric") {
                given(ExternalMetricSourceDslBuilder())
            }

            requireScenario("target") {
                given(ExternalMetricSourceDslBuilder()) {
                    metric {
                        name = PLACEHOLDER
                    }
                }
            }
        }
    }
}