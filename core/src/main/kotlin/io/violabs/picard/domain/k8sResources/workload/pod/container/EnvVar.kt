package io.violabs.picard.domain.k8sResources.workload.pod.container

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.serialization.RetainQuotesSerializer

data class EnvVar(
    val name: String,
    @JsonSerialize(using = RetainQuotesSerializer::class)
    val value: String? = null,
    val valueFrom: EnvVarSource? = null
) {
    class Builder : DSLBuilder<EnvVar> {
        var name: String? = null
        var value: String? = null
        private var valueFrom: EnvVarSource? = null

        fun valueFrom(scope: EnvVarSource.Builder.() -> Unit) {
            valueFrom = EnvVarSource.Builder().apply(scope).build()
        }

        override fun build(): EnvVar {
            return EnvVar(
                name = vRequireNotNull(this::name),
                value = value,
                valueFrom = valueFrom
            )
        }
    }
}