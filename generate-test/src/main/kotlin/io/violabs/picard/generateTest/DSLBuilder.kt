package io.violabs.picard.generateTest

@TestDSLMarker
interface DSLBuilder<T> {
    fun build(): T
}