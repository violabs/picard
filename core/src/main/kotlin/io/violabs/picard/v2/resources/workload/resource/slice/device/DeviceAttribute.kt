package io.violabs.picard.v2.resources.workload.resource.slice.device

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * DeviceAttribute must have exactly one field set.
 */
@GeneratedDsl(withMapGroup = GeneratedDsl.MapGroupTypes.SINGLE)
data class DeviceAttribute(
    /**
     * BoolValue is a true/false value.
     */
    @JsonProperty("bool")
    val boolValue: Boolean? = null,
    /**
     * IntValue is a number.
     */
    @JsonProperty("int")
    val intValue: Long? = null,
    /**
     * StringValue is a string. Must not be longer than 64 characters.
     */
    @JsonProperty("string")
    val stringValue: String? = null,
    /**
     * VersionValue is a semantic version according to semver.org spec 2.0.0. 
     * Must not be longer than 64 characters.
     */
    @JsonProperty("version")
    val versionValue: String? = null
)