package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import java.time.Instant
import java.time.LocalDateTime

data class Event(
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
) : ClusterResource<Event.Version, ObjectMetadata> {
    interface Version : APIVersion

    enum class Type {
        Normal,
        Warning;
    }

    class Builder : ResourceDslBuilder<Event>() {
        var eventTime: Instant? = null
        var action: String? = null
        var deprecatedCount: Int? = null
        var deprecatedFirstTime: LocalDateTime? = null
        var deprecatedLastTime: LocalDateTime? = null
        private var deprecatedSource: EventSource? = null
        var note: String? = null
        var reason: String? = null
        private var regarding: ObjectReference? = null
        private var related: ObjectReference? = null
        var reportingController: String? = null
        var reportingInstance: String? = null
        private var series: EventSeries? = null
        var type: Type? = null

        fun deprecatedSource(scope: EventSource.Builder.() -> Unit) {
            deprecatedSource = EventSource.Builder().apply(scope).build()
        }

        fun regarding(scope: ObjectReference.Builder.() -> Unit) {
            regarding = ObjectReference.Builder().apply(scope).build()
        }

        fun related(scope: ObjectReference.Builder.() -> Unit) {
            related = ObjectReference.Builder().apply(scope).build()
        }

        fun series(scope: EventSeries.Builder.() -> Unit) {
            series = EventSeries.Builder().apply(scope).build()
        }

        override fun build(): Event {
            return Event(
                eventTime = vRequireNotNull(this::eventTime),
                action = action,
                deprecatedCount = deprecatedCount,
                deprecatedFirstTime = deprecatedFirstTime,
                deprecatedLastTime = deprecatedLastTime,
                deprecatedSource = deprecatedSource,
                note = note,
                reason = reason,
                regarding = regarding,
                related = related,
                reportingController = reportingController,
                reportingInstance = reportingInstance,
                series = series,
                type = type
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Event, Builder>(Builder()) {
        fun eventItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}