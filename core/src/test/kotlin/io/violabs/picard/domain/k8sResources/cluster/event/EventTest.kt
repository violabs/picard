package io.violabs.picard.domain.k8sResources.cluster.event


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventTest : FullBuildSim<Event, EventDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Event, EventDslBuilder> {
            scenario {
                id = "minimum"
                given(EventDslBuilder()) {
                    eventTime = TIMESTAMP
                }
                expected = Event(eventTime = TIMESTAMP)
            }

            scenario {
                id = "full"
                given(EventDslBuilder()) {
                    eventTime = TIMESTAMP
                    action = PLACEHOLDER
                    deprecatedCount = 1
                    deprecatedFirstTime = NOW
                    deprecatedLastTime = NOW
                    deprecatedSource {
                        component = PLACEHOLDER
                        host = PLACEHOLDER
                    }
                    note = PLACEHOLDER
                    reason = PLACEHOLDER
                    regarding {
                        sharedObjectReference()
                    }
                    related {
                        sharedObjectReference()
                    }
                    reportingController = PLACEHOLDER
                    reportingInstance = PLACEHOLDER
                    series {
                        count = 1
                        lastObservedTime = TIMESTAMP
                    }
                    type = Event.Type.Normal
                }
                expected = Event(
                    eventTime = TIMESTAMP,
                    action = PLACEHOLDER,
                    deprecatedCount = 1,
                    deprecatedFirstTime = NOW,
                    deprecatedLastTime = NOW,
                    deprecatedSource = EventSource(
                        component = PLACEHOLDER,
                        host = PLACEHOLDER
                    ),
                    note = PLACEHOLDER,
                    reason = PLACEHOLDER,
                    regarding = OBJECT_REFERENCE,
                    related = OBJECT_REFERENCE,
                    reportingController = PLACEHOLDER,
                    reportingInstance = PLACEHOLDER,
                    series = EventSeries(
                        count = 1,
                        lastObservedTime = TIMESTAMP
                    ),
                    type = Event.Type.Normal
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<Event, EventDslBuilder> {
            requireScenario("eventTime") {
                given(EventDslBuilder())
            }
        }
    }
}