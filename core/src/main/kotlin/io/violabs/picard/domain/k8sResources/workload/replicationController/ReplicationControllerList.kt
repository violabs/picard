package io.violabs.picard.domain.k8sResources.workload.replicationController

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sResource

data class ReplicationControllerList(
    override val apiVersion: Version = Version.V1,
    val items: List<ReplicationController>,
    val metadata: ListMeta? = null
) : K8sResource<ReplicationControllerList.Version> {
    override val kind: Kind = Kind.REPLICATION_CONTROLLER_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }
}