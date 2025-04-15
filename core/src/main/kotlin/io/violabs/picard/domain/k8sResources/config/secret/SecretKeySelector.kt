package io.violabs.picard.domain.k8sResources.config.secret

import io.violabs.picard.domain.BaseKeySelector
import io.violabs.picard.domain.DSLBuilder

data class SecretKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
) : BaseKeySelector {
    class Builder : DSLBuilder<SecretKeySelector> {
        var key: String? = null
        var name: String? = null
        var optional: Boolean? = null

        fun optional() {
            optional = true
        }

        override fun build(): SecretKeySelector {
            return SecretKeySelector(
                requireNotNull(key) { "key is required" },
                name,
                optional
            )
        }
    }
}