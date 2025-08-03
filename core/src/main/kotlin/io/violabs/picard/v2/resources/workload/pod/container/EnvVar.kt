package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EnvVar represents an environment variable present in a Container.
 */
@GeneratedDsl(withListGroup = true)
data class EnvVar(
    /**
     * Name of the environment variable. Must be a C_IDENTIFIER.
     */
    val name: String,
    /**
     * Variable references $(VAR_NAME) are expanded using the previously defined environment variables in the container and any service environment variables. If a variable cannot be resolved, the reference in the input string will be unchanged. Double $$ are reduced to a single $, which allows for escaping the $(VAR_NAME) syntax: i.e. "$$(VAR_NAME)" will produce the string literal "$(VAR_NAME)". Escaped references will never be expanded, regardless of whether the variable exists or not. Defaults to "".
     */
    val value: String? = null,
    /**
     * Source for the environment variable's value. Cannot be used if value is not empty.
     */
    val valueFrom: EnvVarSource? = null
)