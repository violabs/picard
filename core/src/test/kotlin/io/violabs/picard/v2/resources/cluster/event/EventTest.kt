package io.violabs.picard.v2.resources.cluster.event

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.ObjectReference
import org.junit.jupiter.api.BeforeAll

class EventTest : SuccessBuildSim<Event, EventV2DslBuilder>() {
    companion object {
        private val OBJECT_REFERENCE = ObjectReference(
            apiVersion = KAPIVersion.V1,
            fieldPath = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            resourceVersion = PLACEHOLDER,
            uid = PLACEHOLDER
        )

        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EventTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Event, EventV2DslBuilder> {
            scenario {
                id = "minimum"
                given(EventV2DslBuilder()) {
                    eventTime = TIMESTAMP
                }
                expected = Event(eventTime = TIMESTAMP)
            }

            scenario {
                id = "full"
                given(EventV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    eventTime = TIMESTAMP
                    action = PLACEHOLDER
                    deprecatedCount = 1
                    deprecatedFirstTimestamp = NOW
                    deprecatedLastTimestamp = NOW
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
                    metadata = OBJECT_META,
                    eventTime = TIMESTAMP,
                    action = PLACEHOLDER,
                    deprecatedCount = 1,
                    deprecatedFirstTimestamp = NOW,
                    deprecatedLastTimestamp = NOW,
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
    }
}