package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class LimitResponse(
    val type: Type,
    val queuing: QueuingConfiguration? = null
) {
    enum class Type {
        Queue,
        Reject
    }

    class Builder : DslBuilder<LimitResponse> {
        var type: Type? = null
        private var queuing: QueuingConfiguration? = null

        fun queuing(scope: QueuingConfiguration.Builder.() -> Unit) {
            this.queuing = QueuingConfiguration.Builder().apply(scope).build()
        }

        override fun build(): LimitResponse {
            return LimitResponse(
                type = vRequireNotNull(this::type),
                queuing = queuing
            )
        }
    }
}
