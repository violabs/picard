package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationListResource

data class ClusterRoleBindingList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<ClusterRoleBinding>,
    override val metadata: ListMeta? = null
) : AuthorizationListResource<ClusterRoleBindingList.Version, ClusterRoleBinding> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ClusterRoleBinding,
        ClusterRoleBinding.Builder,
        ClusterRoleBinding.Group,
        ClusterRoleBindingList
        >(ClusterRoleBinding.Group()) {

        override fun build(): ClusterRoleBindingList {
            return ClusterRoleBindingList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
