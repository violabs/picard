package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewDslBuilder
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewDslBuilderScope
import io.violabs.picard.v2.resources.authorization.review.access.subject.local.LocalSubjectAccessReviewDslBuilder
import io.violabs.picard.v2.resources.authorization.review.access.subject.local.LocalSubjectAccessReviewDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleListDslBuilder
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleListDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBindingDslBuilder
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBindingDslBuilderScope
import io.violabs.picard.v2.resources.authorization.review.access.subject.self.SelfSubjectAccessReviewDslBuilder
import io.violabs.picard.v2.resources.authorization.review.access.subject.self.SelfSubjectAccessReviewDslBuilderScope
import io.violabs.picard.v2.resources.authorization.review.rules.SelfSubjectRulesReviewDslBuilder
import io.violabs.picard.v2.resources.authorization.review.rules.SelfSubjectRulesReviewDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleDslBuilder
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.RoleDslBuilder
import io.violabs.picard.v2.resources.authorization.role.RoleDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.RoleListDslBuilder
import io.violabs.picard.v2.resources.authorization.role.RoleListDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBindingListDslBuilder
import io.violabs.picard.v2.resources.authorization.role.binding.ClusterRoleBindingListDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBindingDslBuilder
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBindingDslBuilderScope
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBindingListDslBuilder
import io.violabs.picard.v2.resources.authorization.role.binding.RoleBindingListDslBuilderScope

interface AuthorizationResource<T : APIVersion, META> : K8sResource<T, META>
interface AuthorizationListResource<T : APIVersion, E> : K8sListResource<T, E>

data class AuthorizationResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {
    class Builder(
        private val resources: MutableList<AuthorizationResource<*, *>> = mutableListOf(),
        private val lists: MutableList<AuthorizationListResource<*, *>> = mutableListOf()
    ) : DslBuilder<AuthorizationResourceSection> {
        fun localSubjectAccessReview(block: LocalSubjectAccessReviewDslBuilderScope) {
            val resource = LocalSubjectAccessReviewDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun selfSubjectAccessReview(block: SelfSubjectAccessReviewDslBuilderScope) {
            val resource = SelfSubjectAccessReviewDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun selfSubjectRulesReview(block: SelfSubjectRulesReviewDslBuilderScope) {
            val resource = SelfSubjectRulesReviewDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun subjectAccessReview(block: SubjectAccessReviewDslBuilderScope) {
            val resource = SubjectAccessReviewDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRole(block: ClusterRoleDslBuilderScope) {
            val resource = ClusterRoleDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRoleList(block: ClusterRoleListDslBuilderScope) {
            val list = ClusterRoleListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun clusterRoleBinding(block: ClusterRoleBindingDslBuilderScope) {
            val resource = ClusterRoleBindingDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun clusterRoleBindingList(block: ClusterRoleBindingListDslBuilderScope) {
            val list = ClusterRoleBindingListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun role(block: RoleDslBuilderScope) {
            val resource = RoleDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun roleList(block: RoleListDslBuilderScope) {
            val list = RoleListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun roleBinding(block: RoleBindingDslBuilderScope) {
            val resource = RoleBindingDslBuilder().apply(block).build()
            resources.add(resource)
        }

        fun roleBindingList(block: RoleBindingListDslBuilderScope) {
            val list = RoleBindingListDslBuilder().apply(block).build()
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