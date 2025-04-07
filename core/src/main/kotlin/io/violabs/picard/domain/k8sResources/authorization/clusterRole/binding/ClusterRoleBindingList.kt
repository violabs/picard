package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterRoleBindingList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<ClusterRoleBinding>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterRoleBindingList.Version, ClusterRoleBinding> {
    interface Version : APIVersion
}
