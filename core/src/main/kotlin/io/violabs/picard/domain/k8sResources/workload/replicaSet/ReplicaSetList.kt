package io.violabs.picard.domain.k8sResources.workload.replicaSet

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sResource

data class ReplicaSetList(
    override val apiVersion: Version = Version.APPS_V1,
    val items: List<ReplicaSet>,
    val metadata: ListMeta? = null
) : K8sResource<ReplicaSetList.Version> {
    override val kind: Kind = Kind.REPLICA_SET_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        APPS_V1("apps/v1");

        override fun toString(): String = refString()
    }
}