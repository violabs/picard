package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class RoleRef(
    val apiGroup: String,
    val kind: String,
    val name: String
) {
    class Builder : DSLBuilder<RoleRef> {
        var apiGroup: String? = null
        var kind: String? = null
        var name: String? = null
        override fun build(): RoleRef {
            return RoleRef(
                apiGroup = vRequireNotNull(this::apiGroup),
                kind = vRequireNotNull(this::kind),
                name = vRequireNotNull(this::name)
            )
        }
    }
}
