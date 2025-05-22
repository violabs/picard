package io.violabs.picard.dsl

interface CoreDslBuilder<T> {
    fun build(): T
}