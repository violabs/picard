package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Toleration

data class RuntimeClassScheduling(
    val nodeSelector: Map<String, String>? = null,
    val tolerations: List<Toleration>? = null
) {
    class Builder : DslBuilder<RuntimeClassScheduling> {
        private var nodeSelector: Map<String, String>? = null
        private var tolerations: List<Toleration>? = null

        fun nodeSelector(vararg nodeSelector: Pair<String, String>) {
            this.nodeSelector = nodeSelector.toMap()
        }

        fun tolerations(scope: Toleration.Group.() -> Unit) {
            this.tolerations = Toleration.Group().apply(scope).tolerations()
        }

        override fun build(): RuntimeClassScheduling {
            return RuntimeClassScheduling(
                nodeSelector = nodeSelector,
                tolerations = tolerations
            )
        }
    }
}