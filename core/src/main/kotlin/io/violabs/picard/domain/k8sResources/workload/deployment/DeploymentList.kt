package io.violabs.picard.domain.k8sResources.workload.deployment

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.workload.deployment.DeploymentListV2"))
data class DeploymentList(
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<Deployment>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<DeploymentList.Version, Deployment> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        Deployment,
        Deployment.Builder,
        Deployment.Group,
        DeploymentList>(Deployment.Group()) {

        override fun build(): DeploymentList {
            return DeploymentList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}