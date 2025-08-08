package io.violabs.picard.v2.resources.cluster.event


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventSourceTest : SuccessBuildSim<EventSource, EventSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventSourceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EventSource, EventSourceDslBuilder> {
            scenario {
                id = "minimum"
                given(EventSourceDslBuilder())
                expected = EventSource()
            }

            scenario {
                id = "full"
                given(EventSourceDslBuilder()) {
                    component = PLACEHOLDER
                    host = PLACEHOLDER
                }
                expected = EventSource(
                    component = PLACEHOLDER,
                    host = PLACEHOLDER
                )
            }
        }
    }
}