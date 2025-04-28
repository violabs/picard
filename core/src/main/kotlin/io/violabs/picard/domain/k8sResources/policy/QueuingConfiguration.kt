package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.DSLBuilder

data class QueuingConfiguration(
    val handSize: Int? = null,
    val queueLengthLimit: Int? = null,
    val queues: Int? = null
) {
    class Builder : DSLBuilder<QueuingConfiguration> {
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