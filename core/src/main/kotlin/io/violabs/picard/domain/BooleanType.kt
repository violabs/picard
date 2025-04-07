package io.violabs.picard.domain

enum class BooleanType : K8sEnum {
    TRUE,
    FALSE,
    UNKNOWN;

    override fun toString(): String = properCase()
}