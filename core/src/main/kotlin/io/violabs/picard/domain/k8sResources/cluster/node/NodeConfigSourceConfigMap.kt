package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class NodeConfigSourceConfigMap(
    val kubeletConfigKey: String,
    val name: String,
    val namespace: String,
    val resourceVersion: String? = null,
    val uid: String? = null
) {
    class Builder : DSLBuilder<NodeConfigSourceConfigMap> {
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