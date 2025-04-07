package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.K8sEnum

data class LimitResponse(
    val type: Type,
    val queuing: QueuingConfiguration? = null
) {
    enum class Type : K8sEnum {
        QUEUE,
        REJECT;

        override fun toString(): String = properCase()
    }
}
