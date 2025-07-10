package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class RuntimeClassOverhead(val podFixed: Map<String, Quantity>? = null) {
    class Builder : DslBuilder<RuntimeClassOverhead> {
        private var podFixed: Map<String, Quantity>? = null

        fun podFixed(vararg podFixed: Pair<String, Quantity>) {
            this.podFixed = podFixed.toMap()
        }

        override fun build(): RuntimeClassOverhead {
            return RuntimeClassOverhead(podFixed)
        }
    }
}