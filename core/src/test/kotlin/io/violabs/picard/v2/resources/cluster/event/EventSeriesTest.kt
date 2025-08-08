package io.violabs.picard.v2.resources.cluster.event


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventSeriesTest : FailureBuildSim<EventSeries, EventSeriesDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventSeriesTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<EventSeries, EventSeriesDslBuilder> {
            requireScenario("count") {
                given(EventSeriesDslBuilder())
            }

            requireScenario("lastObservedTime") {
                given(EventSeriesDslBuilder()) {
                    count = 1
                }
            }
        }
    }
}