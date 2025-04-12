package io.violabs.picard.domain.k8sResources.workload.job

data class PodSuccessPolicy(
    val rules: List<Rule>? = null
) {
    data class Rule(
        val succeededCount: Int? = null,
        val succeededIndexes: String? = null
    )
}