package io.violabs.picard.domain.k8sResources.config.secret

import io.violabs.picard.domain.BaseEnvSource
import io.violabs.picard.common.DSLBuilder

data class SecretEnvSource(
    val name: String? = null,
    val optional: Boolean? = null
) : BaseEnvSource {
    class Builder : DSLBuilder<SecretEnvSource> {
        var name: String? = null
        private var optional: Boolean? = null

        fun optional() {
            this.optional = true
        }

        override fun build(): SecretEnvSource {
            return SecretEnvSource(
                name = name,
                optional = optional
            )
        }
    }
}