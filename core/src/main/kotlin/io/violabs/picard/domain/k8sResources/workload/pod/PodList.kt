package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sResource

data class PodList(
    override val apiVersion: Version = Version.V1,
    val metadata: ListMeta,
    val items: List<_root_ide_package_.io.violabs.picard.domain.k8sResources.workload.pod.Pod>
) : K8sResource<PodList.Version> {
    override val kind: Kind = Kind.POD_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }
}