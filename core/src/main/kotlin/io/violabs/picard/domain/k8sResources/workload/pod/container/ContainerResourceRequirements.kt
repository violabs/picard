package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class ContainerResourceRequirements(
    val claims: List<ContainerResourceClaim>? = null,
    val limits: Map<String, Quantity>? = null,
    val requests: Map<String, Quantity>? = null,
    val resizePolicy: List<ResizePolicy>? = null
) {
    class Builder : DslBuilder<ContainerResourceRequirements> {
        private var claims: List<ContainerResourceClaim>? = null
        private var limits: MutableMap<String, Quantity>? = null
        private var requests: MutableMap<String, Quantity>? = null
        private var resizePolicy: List<ResizePolicy>? = null

        fun claims(scope: ContainerResourceClaim.Group.() -> Unit) {
            claims = ContainerResourceClaim.Group().apply(scope).claims()
        }

        fun limits(scope: MutableMap<String, Quantity>.() -> Unit) {
            if (limits == null) {
                limits = mutableMapOf()
            }

            limits!!.apply(scope)
        }

        fun requests(scope: MutableMap<String, Quantity>.() -> Unit) {
            if (requests == null) {
                requests = mutableMapOf()
            }

            requests!!.apply(scope)
        }

        fun resizePolicy(scope: ResizePolicy.Group.() -> Unit) {
            resizePolicy = ResizePolicy.Group().apply(scope).policies()
        }

        override fun build(): ContainerResourceRequirements {
            return ContainerResourceRequirements(
                claims,
                limits,
                requests,
                resizePolicy
            )
        }
    }
}