package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.K8sEnum

data class FlowDistinguisherMethod(
    val type: String
) {

    enum class Type : K8sEnum {
        BY_USER,
        BY_NAMESPACE;

        override fun toString(): String = properCase()
    }
}
