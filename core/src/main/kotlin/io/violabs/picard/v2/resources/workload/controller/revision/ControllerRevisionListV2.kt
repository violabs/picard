package io.violabs.picard.v2.resources.workload.controller.revision

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ControllerRevisionListV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val items: List<ControllerRevisionV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<ControllerRevisionListV2.Version, ControllerRevisionV2> {
    interface Version : APIVersion
}
