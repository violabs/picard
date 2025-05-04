package io.violabs.picard.domain.k8sResources.authorization.clusterRole

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationListResource

data class ClusterRoleList(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val items: List<ClusterRole>,
    override val metadata: ListMeta? = null
) : AuthorizationListResource<ClusterRoleList.Version, ClusterRole> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ClusterRole,
        ClusterRole.Builder,
        ClusterRole.Group,
        ClusterRoleList
        >(ClusterRole.Group()) {
        override fun build(): ClusterRoleList {
            return ClusterRoleList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
