package io.violabs.picard.domain.k8sResources.workload.replicationController

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ReplicationControllerList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ReplicationController>,
    override val metadata: ListMeta? = null
) : K8sListResource<ReplicationControllerList.Version, ReplicationController> {
    interface Version : APIVersion
}