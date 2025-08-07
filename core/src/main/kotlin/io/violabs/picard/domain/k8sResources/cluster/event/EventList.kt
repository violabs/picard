package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.cluster.event.EventListV2"))
data class EventList(
    override val apiVersion: Version = KAPIVersion.EventsV1,
    override val items: List<Event>,
    override val metadata: ListMeta? = null
) : ClusterListResource<EventList.Version, Event> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        Event,
        Event.Builder,
        Event.Group,
        EventList
        >(Event.Group()) {
        override fun build(): EventList {
            return EventList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
