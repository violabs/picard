package io.violabs.picard.v2.resources.authorization.role

import io.violabs.picard.Common

interface RoleTest {
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
}