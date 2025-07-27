package io.violabs.picard.v2.resources.authorization.role

import io.violabs.picard.Common
import io.violabs.picard.v2.resources.authorization.role.binding.RoleRef
import io.violabs.picard.v2.resources.authorization.role.binding.RoleRefDslBuilder
import io.violabs.picard.v2.resources.authorization.role.binding.Subject
import io.violabs.picard.v2.resources.authorization.role.binding.SubjectDslBuilder

interface RoleTestConfig {
    fun PolicyRuleDslBuilder.Group.sharedPolicyRule() = policyRule {
        verbs(Common.PLACEHOLDER)
        apiGroups(Common.PLACEHOLDER)
        resources(Common.PLACEHOLDER)
        resourceNames(Common.PLACEHOLDER)
        nonResourceURLs(Common.PLACEHOLDER)
    }

    val policyRule: PolicyRule
        get() = PolicyRule(
            verbs = Common.PLACEHOLDER_LIST,
            apiGroups = Common.PLACEHOLDER_LIST,
            resources = Common.PLACEHOLDER_LIST,
            resourceNames = Common.PLACEHOLDER_LIST,
            nonResourceURLs = Common.PLACEHOLDER_LIST
        )

    fun RoleRefDslBuilder.sharedRoleRef() {
        name = Common.PLACEHOLDER
        kind = Common.PLACEHOLDER
        apiGroup = Common.PLACEHOLDER
    }

    val roleRef: RoleRef
        get() = RoleRef(
            apiGroup = Common.PLACEHOLDER,
            kind = Common.PLACEHOLDER,
            name = Common.PLACEHOLDER
        )

    fun SubjectDslBuilder.Group.sharedSubject() {
        subject {
            kind = Subject.Kind.User
            name = Common.PLACEHOLDER
            namespace = Common.PLACEHOLDER
            apiGroup = Common.PLACEHOLDER
        }
    }

    val subject: Subject
        get() = Subject(
            kind = Subject.Kind.User,
            name = Common.PLACEHOLDER,
            namespace = Common.PLACEHOLDER,
            apiGroup = Common.PLACEHOLDER
        )
}