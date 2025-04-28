package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.policy.rule.NonResourcePolicyRule
import io.violabs.picard.domain.k8sResources.policy.rule.ResourcePolicyRule

data class PolicyRulesWithSubjects(
    val subjects: List<PolicyRuleSubject>,
    val nonResourceRules: List<NonResourcePolicyRule>? = null,
    val resourceRules: List<ResourcePolicyRule>? = null
) {
    class Builder : DSLBuilder<PolicyRulesWithSubjects> {
        private var subjects: List<PolicyRuleSubject>? = null
        private var nonResourceRules: List<NonResourcePolicyRule>? = null
        private var resourceRules: List<ResourcePolicyRule>? = null

        fun subjects(scope: PolicyRuleSubject.Group.() -> Unit) {
            subjects = PolicyRuleSubject.Group().apply(scope).subjects()
        }

        fun nonResourceRules(scope: NonResourcePolicyRule.Group.() -> Unit) {
            nonResourceRules = NonResourcePolicyRule.Group().apply(scope).rules()
        }

        fun resourceRules(scope: ResourcePolicyRule.Group.() -> Unit) {
            resourceRules = ResourcePolicyRule.Group().apply(scope).rules()
        }

        override fun build(): PolicyRulesWithSubjects {
            return PolicyRulesWithSubjects(
                subjects = vRequireNotEmpty(this::subjects),
                nonResourceRules = nonResourceRules,
                resourceRules = resourceRules
            )
        }
    }

    class Group : BuilderGroup<PolicyRulesWithSubjects, Builder>(Builder()) {
        fun ruleGroups(): List<PolicyRulesWithSubjects>? = items()

        fun item(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}