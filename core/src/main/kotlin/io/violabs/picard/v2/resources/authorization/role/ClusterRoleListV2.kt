package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterRoleListV2(
    @DefaultValue(
        "KAPIVersion.RbacAuthorizationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    override val items: List<ClusterRoleV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterRoleListV2.Version, ClusterRoleV2> {
    interface Version : APIVersion
}
