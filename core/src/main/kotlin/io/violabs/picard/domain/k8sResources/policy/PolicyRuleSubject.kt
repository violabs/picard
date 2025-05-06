package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSubject
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.policy.subject.GroupSubject
import io.violabs.picard.domain.k8sResources.policy.subject.ServiceAccountSubject
import io.violabs.picard.domain.k8sResources.policy.subject.UserSubject

data class PolicyRuleSubject(
    val kind: String,
    val group: GroupSubject? = null,
    val serviceAccount: ServiceAccountSubject? = null,
    val user: UserSubject? = null
) : BaseSubject {
    class Builder : DSLBuilder<PolicyRuleSubject> {
        var kind: String? = null
        private var group: GroupSubject? = null
        private var serviceAccount: ServiceAccountSubject? = null
        private var user: UserSubject? = null

        fun group(name: String) {
            group = GroupSubject(name)
        }

        fun serviceAccount(scope: ServiceAccountSubject.Builder.() -> Unit) {
            serviceAccount = ServiceAccountSubject.Builder().apply(scope).build()
        }

        fun user(name: String) {
            user = UserSubject(name)
        }

        override fun build(): PolicyRuleSubject {
            return PolicyRuleSubject(
                kind = vRequireNotNull(this::kind),
                group = group,
                serviceAccount = serviceAccount,
                user = user
            )
        }
    }

    class Group : BuilderGroup<PolicyRuleSubject, Builder>(Builder()) {
        fun subjects(): List<PolicyRuleSubject>? = items()

        fun addPolicyRuleSubject(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}