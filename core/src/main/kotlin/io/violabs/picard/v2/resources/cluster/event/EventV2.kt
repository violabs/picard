package io.violabs.picard.v2.resources.cluster.event

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.common.ObjectReference
import java.time.Instant
import java.time.LocalDateTime

/**
 * Event is a report of an event somewhere in the cluster. It generally denotes some state change in the system.
 * Events have a limited retention time and triggers and messages may evolve with time. Event consumers should not
 * rely on the timing of an event with a given Reason reflecting a consistent underlying trigger, or the continued
 * existence of events with that Reason. Events should be treated as informative, best-effort, supplemental data.
 *
 * apiVersion: events.k8s.io/v1
 *
 * import "k8s.io/api/events/v1"
 */
@GeneratedDsl(withListGroup = true)
data class EventV2(
    @DefaultValue(
        "KAPIVersion.EventsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.EventsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * eventTime is the time when this Event was first observed. It is required.
     */
    val eventTime: Instant,
    /**
     * action is what action was taken/failed regarding to the regarding object. It is machine-readable.
     * This field cannot be empty for new Events and it can have at most 128 characters.
     */
    val action: String? = null,
    /**
     * deprecatedCount is the deprecated field assuring backward compatibility with core.v1 Event type.
     */
    val deprecatedCount: Int? = null,
    /**
     * deprecatedFirstTimestamp is the deprecated field assuring backward compatibility with core.v1 Event type.
     */
    val deprecatedFirstTimestamp: LocalDateTime? = null,
    /**
     * deprecatedLastTimestamp is the deprecated field assuring backward compatibility with core.v1 Event type.
     */
    val deprecatedLastTimestamp: LocalDateTime? = null,
    /**
     * deprecatedSource is the deprecated field assuring backward compatibility with core.v1 Event type.
     */
    val deprecatedSource: EventSource? = null,
    /**
     * note is a human-readable description of the status of this operation. Maximal length of the note is 1kB,
     * but libraries should be prepared to handle values up to 64kB.
     */
    val note: String? = null,
    /**
     * reason is why the action was taken. It is human-readable. This field cannot be empty for new Events
     * and it can have at most 128 characters.
     */
    val reason: String? = null,
    /**
     * regarding contains the object this Event is about. In most cases it's an Object reporting controller implements,
     * e.g. ReplicaSetController implements ReplicaSets and this event is emitted because it acts on some changes
     * in a ReplicaSet object.
     */
    val regarding: ObjectReference? = null,
    /**
     * related is the optional secondary object for more complex actions. E.g. when regarding object triggers
     * a creation or deletion of related object.
     */
    val related: ObjectReference? = null,
    /**
     * reportingController is the name of the controller that emitted this Event, e.g. kubernetes.io/kubelet.
     * This field cannot be empty for new Events.
     */
    val reportingController: String? = null,
    /**
     * reportingInstance is the ID of the controller instance, e.g. kubelet-xyzf. This field cannot be empty
     * for new Events and it can have at most 128 characters.
     */
    val reportingInstance: String? = null,
    /**
     * series is data about the Event series this event represents or nil if it's a singleton Event.
     */
    val series: EventSeries? = null,
    /**
     * type is the type of this event (Normal, Warning), new types could be added in the future.
     * It is machine-readable. This field cannot be empty for new Events.
     */
    val type: Type? = null
) : ClusterResource<EventV2.Version, ObjectMeta> {
    interface Version : APIVersion

    enum class Type {
        Normal,
        Warning
    }
}