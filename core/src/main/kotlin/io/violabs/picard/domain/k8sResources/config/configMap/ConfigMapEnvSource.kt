package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.domain.BaseEnvSource
import io.violabs.picard.domain.DslBuilder

data class ConfigMapEnvSource(
    val name: String? = null,
    val optional: Boolean? = null
) : BaseEnvSource {
    class Builder : DslBuilder<ConfigMapEnvSource> {
        var name: String? = null
        private var optional: Boolean? = null

        fun optional() {
            this.optional = true
        }

        override fun build(): ConfigMapEnvSource {
            return ConfigMapEnvSource(
                name = name,
                optional = optional
            )
        }
    }
}