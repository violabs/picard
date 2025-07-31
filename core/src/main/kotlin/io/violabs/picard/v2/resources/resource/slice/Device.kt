package io.violabs.picard.v2.resources.resource.slice

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector
import java.time.LocalDateTime

@GeneratedDsl(withListGroup = true)
data class Device(
    val name: String,
    val allNodes: Boolean? = null,
    val attributes: Map<String, DeviceAttribute>? = null,
    val capacity: Map<String, DeviceCapacity>? = null,
    val consumesCounters: List<DeviceCounterConsumption>? = null,
    val nodeName: String? = null,
    val nodeSelector: NodeSelector? = null,
    val taints: List<DeviceTaint>? = null
)

@GeneratedDsl
data class DeviceAttribute(
    @JsonProperty("bool")
    val boolValue: Boolean? = null,
    @JsonProperty("int")
    val intValue: Long? = null,
    @JsonProperty("string")
    val stringValue: String? = null,
    @JsonProperty("version")
    val versionValue: String? = null
)

@GeneratedDsl
data class DeviceCapacity(
    val value: io.violabs.picard.domain.k8sResources.Quantity
)

@GeneratedDsl(withListGroup = true)
data class DeviceCounterConsumption(
    val counterSet: String,
    val counters: Map<String, Counter>? = null
)

@GeneratedDsl
data class Counter(
    val value: io.violabs.picard.domain.k8sResources.Quantity
)

@GeneratedDsl(withListGroup = true)
data class DeviceTaint(
    val effect: String,
    val key: String,
    val timeAdded: LocalDateTime? = null,
    val value: String? = null
)