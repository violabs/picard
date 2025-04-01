package io.violabs.picard.domain

enum class RestartPolicy : K8sEnum {
    ALWAYS,
    ON_FAILURE,
    NEVER;

    override fun toString(): String = properCase()
}