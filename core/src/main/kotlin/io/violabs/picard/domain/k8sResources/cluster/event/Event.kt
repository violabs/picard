package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.domain.K8sEnum
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import java.time.Instant
import java.time.LocalDateTime

class Event(
    override val apiVersion: Version = KAPIVersion.EventsV1,
    override val metadata: ObjectMetadata? = null,
    val eventTime: Instant,
    val action: String? = null,
    val deprecatedCount: Int? = null,
    val deprecatedFirstTime: LocalDateTime? = null,
    val deprecatedLastTime: LocalDateTime? = null,
    val deprecatedSource: EventSource? = null,
    val note: String? = null,
    val reason: String? = null,
    val regarding: ObjectReference? = null,
    val related: ObjectReference? = null,
    val reportingController: String? = null,
    val reportingInstance: String? = null,
    val series: EventSeries? = null,
    val type: Type? = null
) : K8sResource<Event.Version> {
    interface Version : APIVersion

    enum class Type : K8sEnum {
        NORMAL,
        WARNING;

        override fun toString(): String = properCase()
    }
}