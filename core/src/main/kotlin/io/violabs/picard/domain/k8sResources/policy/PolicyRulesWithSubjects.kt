package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.k8sResources.policy.rule.NonResourcePolicyRule
import io.violabs.picard.domain.k8sResources.policy.rule.ResourcePolicyRule
import io.violabs.picard.domain.k8sResources.policy.subject.GroupSubject
import io.violabs.picard.domain.k8sResources.policy.subject.ServiceAccountSubject
import io.violabs.picard.domain.k8sResources.policy.subject.UserSubject
import io.violabs.picard.domain.k8sResources.workload.BaseSubject

data class PolicyRulesWithSubjects(
    val subjects: List<Subject>,
    val nonResourceRules: List<NonResourcePolicyRule>? = null,
    val resourceRules: List<ResourcePolicyRule>? = null
) {
    data class Subject(
        val kind: String,
        val group: GroupSubject? = null,
        val serviceAccount: ServiceAccountSubject? = null,
        val user: UserSubject? = null
    ) : BaseSubject
}