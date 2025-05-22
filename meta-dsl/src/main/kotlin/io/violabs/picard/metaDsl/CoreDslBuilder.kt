package io.violabs.picard.metaDsl

interface CoreDslBuilder<T> {
    fun build(): T
}