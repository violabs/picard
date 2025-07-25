package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.DslBuilder

data class QueuingConfiguration(
    val handSize: Int? = null,
    val queueLengthLimit: Int? = null,
    val queues: Int? = null
) {
    class Builder : DslBuilder<QueuingConfiguration> {
        var handSize: Int? = null
        var queueLengthLimit: Int? = null
        var queues: Int? = null

        override fun build(): QueuingConfiguration {
            return QueuingConfiguration(
                handSize = handSize,
                queueLengthLimit = queueLengthLimit,
                queues = queues
            )
        }
    }
}