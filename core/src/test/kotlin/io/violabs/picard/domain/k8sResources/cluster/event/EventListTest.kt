package io.violabs.picard.domain.k8sResources.cluster.event


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventListTest : FullBuildSim<EventList, EventList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EventList, EventList.Builder> {
            scenario {
                id = "minimum"
                given(EventList.Builder()) {
                    items {
                        event {
                            eventTime = TIMESTAMP
                        }
                    }
                }
                expected = EventList(
                    items = listOf(Event(eventTime = TIMESTAMP))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<EventList, EventList.Builder> {
            requireNotEmptyScenario("items") {
                given(EventList.Builder())
            }
        }
    }
}