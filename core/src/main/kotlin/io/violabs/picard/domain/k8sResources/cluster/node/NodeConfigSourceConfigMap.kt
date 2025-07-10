package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class NodeConfigSourceConfigMap(
    val kubeletConfigKey: String,
    val name: String,
    val namespace: String,
    val resourceVersion: String? = null,
    val uid: String? = null
) {
    class Builder : DslBuilder<NodeConfigSourceConfigMap> {
        var kubeletConfigKey: String? = null
        var name: String? = null
        var namespace: String? = null
        var resourceVersion: String? = null
        var uid: String? = null

        override fun build(): NodeConfigSourceConfigMap {
            return NodeConfigSourceConfigMap(
                kubeletConfigKey = vRequireNotNull(this::kubeletConfigKey),
                name = vRequireNotNull(this::name),
                namespace = vRequireNotNull(this::namespace),
                resourceVersion = resourceVersion,
                uid = uid
            )
        }
    }
}