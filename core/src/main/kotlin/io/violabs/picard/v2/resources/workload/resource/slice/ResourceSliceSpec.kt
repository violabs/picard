package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.NodeSelector

/**
 * ResourceSliceSpec contains the information published by the driver in one ResourceSlice.
 */
@GeneratedDsl(withListGroup = true)
data class ResourceSliceSpec(
    /**
     * Driver identifies the DRA driver providing the capacity information. 
     * A field selector can be used to list only ResourceSlice objects with a certain driver name.
     * 
     * Must be a DNS subdomain and should end with a DNS domain owned by the vendor of the driver. 
     * This field is immutable.
     */
    val driver: String,
    /**
     * Pool describes the pool that this ResourceSlice belongs to.
     */
    val pool: ResourcePool,
    /**
     * AllNodes indicates that all nodes have access to the resources in the pool.
     * 
     * Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.
     */
    val allNodes: Boolean? = null,
    /**
     * Devices lists some or all of the devices in this pool.
     * 
     * Must not have more than 128 entries.
     */
    val devices: List<Device>? = null,
    /**
     * NodeName identifies the node which provides the resources in this pool. 
     * A field selector can be used to list only ResourceSlice objects belonging to a certain node.
     * 
     * This field can be used to limit access from nodes to ResourceSlices with the same node name. 
     * It also indicates to autoscalers that adding new nodes of the same type as some old node might 
     * also make new resources available.
     * 
     * Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set. 
     * This field is immutable.
     */
    val nodeName: String? = null,
    /**
     * NodeSelector defines which nodes have access to the resources in the pool, 
     * when that pool is not limited to a single node.
     * 
     * Must use exactly one term.
     * 
     * Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.
     */
    val nodeSelector: NodeSelector? = null,
    /**
     * PerDeviceNodeSelection defines whether the access from nodes to resources in the pool 
     * is set on the ResourceSlice level or on each device. If it is set to true, 
     * every device defined the ResourceSlice must specify this individually.
     * 
     * Exactly one of NodeName, NodeSelector, AllNodes, and PerDeviceNodeSelection must be set.
     */
    val perDeviceNodeSelection: Boolean? = null,
    /**
     * SharedCounters defines a list of counter sets, each of which has a name and a list 
     * of counters available.
     * 
     * The names of the SharedCounters must be unique in the ResourceSlice.
     * 
     * The maximum number of counters in all sets is 32.
     */
    val sharedCounters: List<CounterSet>? = null
)