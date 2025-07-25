package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.DslBuilder

data class UncountedTerminatedPods(
    val failed: List<String>? = null,
    val succeeded: List<String>? = null
) {
    class Builder : DslBuilder<UncountedTerminatedPods> {
        private var failed: List<String>? = null
        private var succeeded: List<String>? = null

        fun failed(vararg failed: String) {
            this.failed = failed.toList()
        }

        fun succeeded(vararg succeeded: String) {
            this.succeeded = succeeded.toList()
        }

        override fun build(): UncountedTerminatedPods {
            return UncountedTerminatedPods(
                failed = failed,
                succeeded = succeeded
            )
        }
    }
}