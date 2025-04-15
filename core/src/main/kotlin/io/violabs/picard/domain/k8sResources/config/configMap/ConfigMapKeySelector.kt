package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.domain.DSLBuilder

data class ConfigMapKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
) {
    class Builder : DSLBuilder<ConfigMapKeySelector> {
        var key: String? = null
        var name: String? = null
        var optional: Boolean? = null

        fun optional() {
            optional = true
        }

        override fun build(): ConfigMapKeySelector {
            return ConfigMapKeySelector(
                key = requireNotNull(key) { "key is required" },
                name = name,
                optional = optional
            )
        }
    }
}