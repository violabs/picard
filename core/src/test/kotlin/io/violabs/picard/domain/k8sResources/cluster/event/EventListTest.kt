package io.violabs.picard.domain.k8sResources.cluster.event


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
                        eventItem {
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