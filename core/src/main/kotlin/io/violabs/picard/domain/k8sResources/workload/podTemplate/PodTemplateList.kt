package io.violabs.picard.domain.k8sResources.workload.podTemplate

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sResource

data class PodTemplateList(
    override val apiVersion: Version = Version.V1,
    val metadata: ListMeta? = null,
    val items: List<PodTemplate>? = null
) : K8sResource<PodTemplateList.Version> {
    override val kind: Kind = Kind.CONFIG_MAP_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }
}