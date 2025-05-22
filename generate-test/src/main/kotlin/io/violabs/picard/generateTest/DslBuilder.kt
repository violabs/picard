package io.violabs.picard.generateTest

import io.violabs.picard.metaDsl.CoreDslBuilder

@TestDslMarker
interface DslBuilder<T> : CoreDslBuilder<T> {
    override fun build(): T
}