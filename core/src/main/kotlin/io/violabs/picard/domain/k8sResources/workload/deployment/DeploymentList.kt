package io.violabs.picard.domain.k8sResources.workload.deployment

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sResource

data class DeploymentList(
    override val apiVersion: Version = Version.V1,
    val items: List<Deployment>,
    val metadata: ListMeta? = null
) : K8sResource<DeploymentList.Version> {
    override val kind: Kind = Kind.DEPLOYMENT_LIST

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }
}