package io.violabs.picard.domain.k8sResources.authorization.role.binding

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

data class RoleBinding(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    override val roleRef: RoleRef,
    val subjects: List<K8sSubject>? = null
) : AuthorizationResource<RoleBinding.Version>, K8sRoleBinding {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<RoleBinding>() {
        private var roleRef: RoleRef? = null
        private var subjects: List<K8sSubject>? = null

        fun roleRef(scope: RoleRef.Builder.() -> Unit) {
            roleRef = RoleRef.Builder().apply(scope).build()
        }

        fun subjects(scope: K8sSubject.Group.() -> Unit) {
            subjects = K8sSubject.Group().apply(scope).subjects()
        }

        override fun build(): RoleBinding {
            return RoleBinding(
                roleRef = vRequireNotNull(this::roleRef),
                subjects = subjects,
                metadata = metadata,
            )
        }
    }

    class Group : K8sListResource.ItemGroup<RoleBinding, Builder>(Builder()) {
        fun binding(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}