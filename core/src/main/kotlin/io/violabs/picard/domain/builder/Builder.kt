package io.violabs.picard.domain.builder

interface Builder<T> {
    fun build(): T
}