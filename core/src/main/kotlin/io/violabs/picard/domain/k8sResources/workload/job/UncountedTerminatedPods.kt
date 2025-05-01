package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.DSLBuilder

data class UncountedTerminatedPods(
    val failed: List<String>? = null,
    val succeeded: List<String>? = null
) {
    class Builder : DSLBuilder<UncountedTerminatedPods> {
        private var failed: List<String>? = null
        private var succeeded: List<String>? = null

        override fun build(): UncountedTerminatedPods {
            return UncountedTerminatedPods(
                failed = failed,
                succeeded = succeeded
            )
        }
    }
}