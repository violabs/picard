package io.violabs.picard.domain.k8sResources.authorization.role.binding

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class RoleBindingList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<RoleBinding>,
    override val metadata: ListMeta? = null
) : K8sListResource<RoleBindingList.Version, RoleBinding> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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
