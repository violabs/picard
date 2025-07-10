package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.DslBuilder

data class ParamKind(
    val apiVersion: String? = null,
    val kind: String? = null
) {
    class Builder : DslBuilder<ParamKind> {
        var apiVersion: String? = null
        var kind: String? = null

        override fun build(): ParamKind {
            return ParamKind(
                apiVersion = apiVersion,
                kind = kind
            )
        }
    }

}