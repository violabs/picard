package io.violabs.picard.v2.resources.workload.resource.slice.device

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.workload.resource.NodeSelector
import io.violabs.picard.v2.resources.workload.resource.device.taint.DeviceTaint

/**
 * Device represents one individual hardware instance that can be selected based on its attributes. 
 * Besides the name, exactly one field must be set.
 */
@GeneratedDsl(withListGroup = true)
data class Device(
    /**
     * Name is unique identifier among all devices managed by the driver in the pool. 
     * It must be a DNS label.
     */
    val name: String,
    /**
     * AllNodes indicates that all nodes have access to the device.
     * 
     * Must only be set if Spec.PerDeviceNodeSelection is set to true. 
     * At most one of NodeName, NodeSelector and AllNodes can be set.
     */
    val allNodes: Boolean? = null,
    /**
     * Attributes defines the set of attributes for this device. 
     * The name of each attribute must be unique in that set.
     * 
     * The maximum number of attributes and capacities combined is 32.
     */
    val attributes: Map<String, DeviceAttribute>? = null,
    /**
     * Capacity defines the set of capacities for this device. 
     * The name of each capacity must be unique in that set.
     * 
     * The maximum number of attributes and capacities combined is 32.
     */
    val capacity: Map<String, DeviceCapacity>? = null,
    /**
     * ConsumesCounters defines a list of references to sharedCounters and the set of 
     * counters that the device will consume from those counter sets.
     * 
     * There can only be a single entry per counterSet.
     * 
     * The total number of device counter consumption entries must be <= 32. 
     * In addition, the total number in the entire ResourceSlice must be <= 1024 
     * (for example, 64 devices with 16 counters each).
     */
    val consumesCounters: List<DeviceCounterConsumption>? = null,
    /**
     * NodeName identifies the node where the device is available.
     * 
     * Must only be set if Spec.PerDeviceNodeSelection is set to true. 
     * At most one of NodeName, NodeSelector and AllNodes can be set.
     */
    val nodeName: String? = null,
    /**
     * NodeSelector defines the nodes where the device is available.
     * 
     * Must use exactly one term.
     * 
     * Must only be set if Spec.PerDeviceNodeSelection is set to true. 
     * At most one of NodeName, NodeSelector and AllNodes can be set.
     */
    val nodeSelector: NodeSelector? = null,
    /**
     * If specified, these are the driver-defined taints.
     * 
     * The maximum number of taints is 4.
     * 
     * This is an alpha field and requires enabling the DRADeviceTaints feature gate.
     */
    val taints: List<DeviceTaint>? = null
)