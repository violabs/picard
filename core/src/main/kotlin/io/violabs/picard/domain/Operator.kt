package io.violabs.picard.domain

enum class Operator : K8sEnum {
    IN,
    NOT_IN;

    override fun toString() = properCase()
}