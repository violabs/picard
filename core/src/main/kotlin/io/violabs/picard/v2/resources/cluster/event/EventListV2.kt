package io.violabs.picard.v2.resources.cluster.event

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterListResource

/**
 * EventList is a list of Events.
 */
@GeneratedDsl
data class EventListV2(
    @DefaultValue(
        "KAPIVersion.EventsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.EventsV1,
    override val items: List<EventV2>,
    override val metadata: ListMeta? = null
) : ClusterListResource<EventListV2.Version, EventV2> {
    interface Version : APIVersion
}
