package io.violabs.picard.domain.k8sResources.config.secret

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseKeySelector
import io.violabs.picard.common.DSLBuilder

data class SecretKeySelector(
    val key: String,
    val name: String? = null,
    val optional: Boolean? = null
) : BaseKeySelector {
    class Builder : DSLBuilder<SecretKeySelector> {
        var key: String? = null
        var name: String? = null
        private var optional: Boolean? = null

        fun optional(value: Boolean = true) {
            optional = value
        }

        override fun build(): SecretKeySelector {
            return SecretKeySelector(
                vRequireNotNull(this::key),
                name,
                optional
            )
        }
    }
}