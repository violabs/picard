package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.domain.BaseAffinity
import io.violabs.picard.domain.DslBuilder

data class Affinity(
    val nodeAffinity: NodeAffinity? = null,
    val podAffinity: PodAffinity? = null,
    val podAntiAffinity: PodAntiAffinity? = null
) : BaseAffinity {
    class Builder : DslBuilder<Affinity> {
        private var nodeAffinity: NodeAffinity? = null
        private var podAffinity: PodAffinity? = null
        private var podAntiAffinity: PodAntiAffinity? = null

        fun nodeAffinity(init: NodeAffinity.Builder.() -> Unit) {
            nodeAffinity = NodeAffinity.Builder().apply(init).build()
        }

        fun podAffinity(init: PodAffinity.Builder.() -> Unit) {
            podAffinity = PodAffinity.Builder().apply(init).build()
        }

        fun podAntiAffinity(init: PodAntiAffinity.Builder.() -> Unit) {
            podAntiAffinity = PodAntiAffinity.Builder().apply(init).build()
        }

        override fun build(): Affinity {
            return Affinity(
                nodeAffinity = nodeAffinity,
                podAffinity = podAffinity,
                podAntiAffinity = podAntiAffinity
            )
        }
    }
}