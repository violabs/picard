package io.violabs.picard.domain.k8sResources.cluster.runtimeClass

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class RuntimeClassOverhead(val podFixed: Map<String, Quantity>? = null) {
    class Builder : DSLBuilder<RuntimeClassOverhead> {
        private var podFixed: Map<String, Quantity>? = null

        fun podFixed(vararg podFixed: Pair<String, Quantity>) {
            this.podFixed = podFixed.toMap()
        }

        override fun build(): RuntimeClassOverhead {
            return RuntimeClassOverhead(podFixed)
        }
    }
}