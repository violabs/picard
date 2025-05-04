package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class ConfigMapKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
) {
    class Builder : DSLBuilder<ConfigMapKeySelector> {
        var key: String? = null
        var name: String? = null
        private var optional: Boolean? = null

        fun optional(value: Boolean = true) {
            optional = value
        }

        override fun build(): ConfigMapKeySelector {
            return ConfigMapKeySelector(
                key = vRequireNotNull(this::key),
                name = name,
                optional = optional
            )
        }
    }
}