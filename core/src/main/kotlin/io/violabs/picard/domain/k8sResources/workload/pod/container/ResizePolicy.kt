package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

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

    class Group : BuilderGroup<ResizePolicy, Builder>(Builder()) {
        fun policies(): List<ResizePolicy>? = items()

        fun addResizePolicy(block: Builder.() -> Unit) {
            add(block)
        }
    }
}