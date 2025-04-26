package io.violabs.picard.domain.k8sResources.cluster.event


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EventSourceTest : SuccessBuildSim<EventSource, EventSource.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventSourceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EventSource, EventSource.Builder> {
            scenario {
                id = "minimum"
                given(EventSource.Builder())
                expected = EventSource()
            }

            scenario {
                id = "full"
                given(EventSource.Builder()) {
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