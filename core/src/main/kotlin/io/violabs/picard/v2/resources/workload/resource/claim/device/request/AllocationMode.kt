package io.violabs.picard.v2.resources.workload.resource.claim.device.request

enum class AllocationMode {
    /**
     * This request is for a specific number of devices. The exact number is provided in the count field.
     */
    ExactCount,

    /**
     * This request is for all of the matching devices in a pool. At least one device must exist on the node
     * for the allocation to succeed. Allocation will fail if some devices are already allocated, unless
     * adminAccess is requested.
     */
    All
}