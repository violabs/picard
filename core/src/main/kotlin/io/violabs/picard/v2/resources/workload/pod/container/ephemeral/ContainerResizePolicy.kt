package io.violabs.picard.v2.resources.workload.pod.container.ephemeral

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ContainerResizePolicy represents resource resize policy for the container.
 */
@GeneratedDsl(withListGroup = true)
data class ContainerResizePolicy(
    /**
     * Name of the resource to which this resource resize policy applies. Supported values: cpu, memory.
     */
    val resourceName: String,
    /**
     * Restart policy to apply when specified resource is resized. If not specified, it defaults to NotRequired.
     */
    val restartPolicy: String
) {
    enum class ResourceName {
        @JsonProperty("cpu")
        Cpu,
        @JsonProperty("memory")
        Memory
    }
}