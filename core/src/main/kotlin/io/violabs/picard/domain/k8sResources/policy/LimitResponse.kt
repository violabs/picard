package io.violabs.picard.domain.k8sResources.policy

data class LimitResponse(
    val type: Type,
    val queuing: QueuingConfiguration? = null
) {
    enum class Type {
        Queue,
        Reject
    }
}
