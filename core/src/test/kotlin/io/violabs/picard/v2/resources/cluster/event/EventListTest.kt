package io.violabs.picard.v2.resources.cluster.event


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventListTest : FullBuildSim<EventList, EventListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EventList, EventListDslBuilder> {
            scenario {
                id = "minimum"
                given(EventListDslBuilder()) {
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

        private val FAILURE_POSSIBILITIES = possibilities<EventList, EventListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(EventListDslBuilder())
            }
        }
    }
}