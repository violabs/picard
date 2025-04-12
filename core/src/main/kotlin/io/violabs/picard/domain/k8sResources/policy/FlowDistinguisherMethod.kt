package io.violabs.picard.domain.k8sResources.policy

data class FlowDistinguisherMethod(
    val type: String
) {

    enum class Type {
        ByUser,
        ByNamespace
    }
}
