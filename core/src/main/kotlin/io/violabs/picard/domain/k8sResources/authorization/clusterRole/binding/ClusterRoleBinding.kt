package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.K8sRoleBinding
import io.violabs.picard.domain.k8sResources.authorization.K8sSubject
import io.violabs.picard.domain.k8sResources.authorization.RoleRef
import io.violabs.picard.domain.manifest.AuthorizationResource

@Deprecated(
    "Use v2",
    ReplaceWith("io.violabs.picard.v2.resources.authorization.clusterRole.binding.ClusterRoleBindingV2")
)
data class ClusterRoleBinding(
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    override val roleRef: RoleRef,
    val subjects: List<K8sSubject>? = null,
    override val metadata: ObjectMetadata? = null
) : AuthorizationResource<ClusterRoleBinding.Version, ObjectMetadata>, K8sRoleBinding {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ClusterRoleBinding>() {
        private var roleRef: RoleRef? = null
        private var subjects: List<K8sSubject>? = null

        fun roleRef(scope: RoleRef.Builder.() -> Unit) {
            roleRef = RoleRef.Builder().apply(scope).build()
        }

        fun subjects(scope: K8sSubject.Group.() -> Unit) {
            subjects = K8sSubject.Group().apply(scope).subjects()
        }

        override fun build(): ClusterRoleBinding {
            return ClusterRoleBinding(
                roleRef = vRequireNotNull(this::roleRef),
                subjects = subjects,
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ClusterRoleBinding, Builder>(Builder()) {
        fun binding(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}