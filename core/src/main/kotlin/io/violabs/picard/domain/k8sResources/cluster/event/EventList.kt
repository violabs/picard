package io.violabs.picard.domain.k8sResources.cluster.event

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class EventList(
    override val apiVersion: Version = KAPIVersion.EventsV1,
    override val items: List<Event>,
    override val metadata: ListMeta? = null
) : K8sListResource<EventList.Version, Event> {
    interface Version : APIVersion
}
