package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class ResizePolicy(
    val resourceName: String,
    val restartPolicy: String
) {
    class Builder : DSLBuilder<ResizePolicy> {
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