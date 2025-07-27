package io.violabs.picard.domain.k8sResources.authorization.role

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.K8sRole
import io.violabs.picard.domain.k8sResources.authorization.PolicyRule
import io.violabs.picard.domain.manifest.AuthorizationResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.authorization.role.RoleV2"))
data class Role(
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    override val rules: List<PolicyRule>? = null
) : AuthorizationResource<Role.Version, ObjectMetadata>, K8sRole {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<Role>() {
        private var rules: List<PolicyRule>? = null

        fun rules(scope: PolicyRule.Group.() -> Unit) {
            rules = PolicyRule.Group().apply(scope).rules()
        }

        override fun build(): Role {
            return Role(
                metadata = metadata,
                rules = rules
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Role, Builder>(Builder()) {
        fun roleItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}