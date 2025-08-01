package io.violabs.picard.v2.resources.workload.resource.slice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceCounterConsumption defines a set of counters that a device will consume from a CounterSet.
 */
@GeneratedDsl(withListGroup = true)
data class DeviceCounterConsumption(
    /**
     * CounterSet is the name of the set from which the counters defined will be consumed.
     */
    val counterSet: String,
    /**
     * Counters defines the counters that will be consumed by the device.
     * 
     * The maximum number counters in a device is 32. In addition, the maximum number 
     * of all counters in all devices is 1024 (for example, 64 devices with 16 counters each).
     */
    val counters: Map<String, Counter>? = null
)