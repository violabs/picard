package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder

data class ResizePolicy(
    val resourceName: String,
    val restartPolicy: String
) {
    class Builder : DslBuilder<ResizePolicy> {
        var resourceName: String? = null
        var restartPolicy: String? = null

        override fun build(): ResizePolicy {
            return ResizePolicy(
                vRequireNotNull(this::resourceName),
                vRequireNotNull(this::restartPolicy)
            )
        }
    }
}