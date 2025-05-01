package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.Operator

data class OnExitCodesRequirement(
    val operator: Operator,
    val values: List<Int>,
    val containerName: String? = null
) {
    class Builder : DSLBuilder<OnExitCodesRequirement> {
        var operator: Operator? = null
        private var values: List<Int>? = null
        var containerName: String? = null

        override fun build(): OnExitCodesRequirement {
            return OnExitCodesRequirement(
                operator = requireNotNull(operator),
                values = requireNotNull(values),
                containerName = containerName
            )
        }
    }
}