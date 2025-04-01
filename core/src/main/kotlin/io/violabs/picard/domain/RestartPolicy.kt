package io.violabs.picard.domain

enum class RestartPolicy(val ref: String) {
    ALWAYS("Always"),
    ON_FAILURE("OnFailure"),
    NEVER("Never");

    override fun toString(): String = ref
}