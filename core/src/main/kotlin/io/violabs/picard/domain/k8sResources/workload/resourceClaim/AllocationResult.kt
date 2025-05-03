package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector

data class AllocationResult(
    val devices: DeviceAllocationResult? = null,
    val nodeSelector: NodeSelector? = null
) {
    class Builder : DSLBuilder<AllocationResult> {
        private var devices: DeviceAllocationResult? = null
        private var nodeSelector: NodeSelector? = null

        fun devices(block: DeviceAllocationResult.Builder.() -> Unit) {
            devices = DeviceAllocationResult.Builder().apply(block).build()
        }

        fun nodeSelector(block: NodeSelector.Builder.() -> Unit) {
            nodeSelector = NodeSelector.Builder().apply(block).build()
        }

        override fun build(): AllocationResult {
            return AllocationResult(
                devices = devices,
                nodeSelector = nodeSelector
            )
        }
    }
}

