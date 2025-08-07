package io.violabs.picard.v2.resources.workload.deployment

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

/**
 * DeploymentList is a list of Deployments.
 */
@GeneratedDsl
data class DeploymentListV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<DeploymentV2>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<DeploymentListV2.Version, DeploymentV2> {
    interface Version : APIVersion
}
