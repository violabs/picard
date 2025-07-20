package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.Operator

data class OnExitCodesRequirement(
    val operator: Operator,
    val values: List<Int>,
    val containerName: String? = null
) {
    class Builder : DslBuilder<OnExitCodesRequirement> {
        var operator: Operator? = null
        private var values: List<Int>? = null
        var containerName: String? = null

        fun values(vararg values: Int) {
            this.values = values.toList()
        }

        override fun build(): OnExitCodesRequirement {
            return OnExitCodesRequirement(
                operator = vRequireNotNull(this::operator),
                values = vRequireNotEmpty(this::values),
                containerName = containerName
            )
        }
    }
}