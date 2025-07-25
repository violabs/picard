package io.violabs.picard.domain.k8sResources.authorization.role.binding

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationListResource

data class RoleBindingList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<RoleBinding>,
    override val metadata: ListMeta? = null
) : AuthorizationListResource<RoleBindingList.Version, RoleBinding> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        RoleBinding,
        RoleBinding.Builder,
        RoleBinding.Group,
        RoleBindingList
        >(RoleBinding.Group()) {

        override fun build(): RoleBindingList {
            return RoleBindingList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
