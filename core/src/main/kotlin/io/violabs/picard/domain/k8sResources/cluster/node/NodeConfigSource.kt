package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DslBuilder

data class NodeConfigSource(
    val configMap: NodeConfigSourceConfigMap? = null
) {
    class Builder : DslBuilder<NodeConfigSource> {
        private var configMap: NodeConfigSourceConfigMap? = null

        fun configMap(scope: NodeConfigSourceConfigMap.Builder.() -> Unit) {
            configMap = NodeConfigSourceConfigMap.Builder().apply(scope).build()
        }

        override fun build(): NodeConfigSource {
            return NodeConfigSource(configMap)
        }
    }
}
