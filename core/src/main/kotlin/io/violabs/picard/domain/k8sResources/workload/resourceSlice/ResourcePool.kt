package io.violabs.picard.domain.k8sResources.workload.resourceSlice

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class ResourcePool(
    val generation: Long,
    val name: String,
    val resourceSliceCount: Long
) {
    class Builder : DslBuilder<ResourcePool> {
        var generation: Long? = null
        var name: String? = null
        var resourceSliceCount: Long? = null

        override fun build(): ResourcePool {
            return ResourcePool(
                generation = vRequireNotNull(this::generation),
                name = vRequireNotNull(this::name),
                resourceSliceCount = vRequireNotNull(this::resourceSliceCount)
            )
        }
    }
}