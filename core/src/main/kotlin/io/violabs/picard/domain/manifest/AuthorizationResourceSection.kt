package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.authorization.accessReview.LocalSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SelfSubjectRulesReview
import io.violabs.picard.domain.k8sResources.authorization.accessReview.SubjectAccessReview
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRole
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.ClusterRoleList
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBinding
import io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding.ClusterRoleBindingList
import io.violabs.picard.domain.k8sResources.authorization.role.Role
import io.violabs.picard.domain.k8sResources.authorization.role.RoleList
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBinding
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBindingList

interface AuthorizationResource<T : APIVersion> : K8sResource<T>
interface AuthorizationListResource<T : APIVersion, E> : K8sListResource<T, E>

data class AuthorizationResourceSection(
    val resources: List<K8sAPIResource<*>>
) : ManifestResource {
    class Builder(
        private val resources: MutableList<AuthorizationResource<*>> = mutableListOf(),
        private val lists: MutableList<AuthorizationListResource<*, *>> = mutableListOf()
    ) : DSLBuilder<AuthorizationResourceSection> {
        fun localSubjectAccessReview(block: LocalSubjectAccessReview.Builder.() -> Unit) {
            val resource = LocalSubjectAccessReview.Builder().apply(block).build()
            resources.add(resource)
        }

        fun selfSubjectAccessReview(block: SelfSubjectAccessReview.Builder.() -> Unit) {
            val resource = SelfSubjectAccessReview.Builder().apply(block).build()
            resources.add(resource)
        }

        fun selfSubjectRulesReview(block: SelfSubjectRulesReview.Builder.() -> Unit) {
            val resource = SelfSubjectRulesReview.Builder().apply(block).build()
            resources.add(resource)
        }

        fun subjectAccessReview(block: SubjectAccessReview.Builder.() -> Unit) {
            val resource = SubjectAccessReview.Builder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRole(block: ClusterRole.Builder.() -> Unit) {
            val resource = ClusterRole.Builder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRoleList(block: ClusterRoleList.Builder.() -> Unit) {
            val list = ClusterRoleList.Builder().apply(block).build()
            lists.add(list)
        }

        fun clusterRoleBinding(block: ClusterRoleBinding.Builder.() -> Unit) {
            val resource = ClusterRoleBinding.Builder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRoleBindingList(block: ClusterRoleBindingList.Builder.() -> Unit) {
            val list = ClusterRoleBindingList.Builder().apply(block).build()
            lists.add(list)
        }

        fun role(block: Role.Builder.() -> Unit) {
            val resource = Role.Builder().apply(block).build()
            resources.add(resource)
        }

        fun roleList(block: RoleList.Builder.() -> Unit) {
            val list = RoleList.Builder().apply(block).build()
            lists.add(list)
        }

        fun roleBinding(block: RoleBinding.Builder.() -> Unit) {
            val resource = RoleBinding.Builder().apply(block).build()
            resources.add(resource)
        }

        fun roleBindingList(block: RoleBindingList.Builder.() -> Unit) {
            val list = RoleBindingList.Builder().apply(block).build()
            lists.add(list)
        }

        override fun build(): AuthorizationResourceSection {
            return AuthorizationResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}