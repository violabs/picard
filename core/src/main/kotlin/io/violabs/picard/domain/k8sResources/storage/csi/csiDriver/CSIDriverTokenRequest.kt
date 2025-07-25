package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class CSIDriverTokenRequest(
    val audience: String,
    val expirationSeconds: Long? = null
) {
    class Builder : DslBuilder<CSIDriverTokenRequest> {
        var audience: String? = null
        var expirationSeconds: Long? = null

        override fun build(): CSIDriverTokenRequest {
            return CSIDriverTokenRequest(
                audience = vRequireNotNull(this::audience),
                expirationSeconds = expirationSeconds
            )
        }
    }

    class Group : BuilderGroup<CSIDriverTokenRequest, Builder>(Builder()) {
        fun requests(): List<CSIDriverTokenRequest>? = items()

        fun addCSIDriverTokenRequest(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}