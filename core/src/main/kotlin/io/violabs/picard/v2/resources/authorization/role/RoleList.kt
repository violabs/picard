package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationListResource

/**
 * RoleList is a list of Roles.
 */
@GeneratedDsl
data class RoleList(
    @DefaultValue(
        "KAPIVersion.RbacAuthorizationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    override val items: List<Role>,
    override val metadata: ListMeta? = null
) : AuthorizationListResource<RoleList.Version, Role> {
    interface Version : APIVersion
}
