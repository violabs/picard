package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ClusterRoleList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<ClusterRole>,
    override val metadata: ListMeta? = null
) : K8sListResource<ClusterRoleList.Version, ClusterRole> {
    interface Version : APIVersion
}
