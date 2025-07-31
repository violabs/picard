package io.violabs.picard.v2.resources.resource.slice

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector

@GeneratedDsl(withListGroup = true)
data class ResourceSliceSpec(
    val driver: String,
    val pool: ResourcePool,
    val allNodes: Boolean? = null,
    val devices: List<Device>? = null,
    val nodeName: String? = null,
    val nodeSelector: NodeSelector? = null,
    val perDeviceNodeSelection: Boolean? = null,
    val sharedCounters: List<CounterSet>? = null
)