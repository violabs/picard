package io.violabs.picard.generateTest

import io.violabs.picard.dsl.CoreDslBuilder

@TestDslMarker
interface DslBuilder<T> : CoreDslBuilder<T> {
    override fun build(): T
}