package io.violabs.picard.domain.k8sResources.cluster.event


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventSeriesTest : FailureBuildSim<EventSeries, EventSeries.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventSeriesTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<EventSeries, EventSeries.Builder> {
            requireScenario("count") {
                given(EventSeries.Builder())
            }

            requireScenario("lastObservedTime") {
                given(EventSeries.Builder()) {
                    count = 1
                }
            }
        }
    }
}