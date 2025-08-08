package io.violabs.picard.v2.resources.workload.resource.claim.template

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.WorkloadListResource

@GeneratedDsl
data class ResourceClaimTemplateList(
    @DefaultValue(
        "KAPIVersion.ResourceV1Beta2",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta2,
    override val items: List<ResourceClaimTemplate>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<ResourceClaimTemplateList.Version, ResourceClaimTemplate> {
    interface Version : APIVersion
}
