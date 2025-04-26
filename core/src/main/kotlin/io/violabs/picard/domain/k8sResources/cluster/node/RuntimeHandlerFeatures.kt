package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.domain.DSLBuilder

data class RuntimeHandlerFeatures(
    val recursiveReadOnlyMounts: Boolean? = null,
    val userNamespaces: Boolean? = null
) {
    class Builder : DSLBuilder<RuntimeHandlerFeatures> {
        private var recursiveReadOnlyMounts: Boolean? = null
        private var userNamespaces: Boolean? = null

        fun recursiveReadOnlyMounts(value: Boolean = true) {
            recursiveReadOnlyMounts = value
        }

        fun userNamespaces(value: Boolean = true) {
            userNamespaces = value
        }

        override fun build(): RuntimeHandlerFeatures {
            return RuntimeHandlerFeatures(recursiveReadOnlyMounts, userNamespaces)
        }
    }
}