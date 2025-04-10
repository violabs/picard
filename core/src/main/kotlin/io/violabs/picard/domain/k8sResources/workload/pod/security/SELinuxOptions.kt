package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.domain.DslBuilder

data class SELinuxOptions(
    val level: String? = null,
    val role: String? = null,
    val type: String? = null,
    val user: String? = null
) {
    class Builder() : DslBuilder<SELinuxOptions> {
        var level: String? = null
        var role: String? = null
        var type: String? = null
        var user: String? = null

        override fun build(): SELinuxOptions {
            return SELinuxOptions(
                level = level,
                role = role,
                type = type,
                user = user
            )
        }
    }
}