package io.violabs.picard.domain

interface DslBuilder<T> {

    fun build(): T
}