package io.violabs.picard.v2.resources.cluster.service.api

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ApiServiceList(
    @DefaultValue(
        "KAPIVersion.APIRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val items: List<ApiService>,
    override val metadata: ListMeta? = null
) : K8sListResource<ApiServiceList.Version, ApiService> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "APIServiceList"
    }
}
