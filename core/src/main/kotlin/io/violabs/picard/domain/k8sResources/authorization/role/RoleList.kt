package io.violabs.picard.domain.k8sResources.authorization.role

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationListResource

data class RoleList(
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    override val items: List<Role>,
    override val metadata: ListMeta? = null
) : AuthorizationListResource<RoleList.Version, Role> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        Role,
        Role.Builder,
        Role.Group,
        RoleList
        >(Role.Group()) {

        override fun build(): RoleList {
            return RoleList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
