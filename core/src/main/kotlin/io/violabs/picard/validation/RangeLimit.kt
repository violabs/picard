package io.violabs.picard.validation


@Retention(AnnotationRetention.RUNTIME)
annotation class RangeLimit(
    val min: Int = Int.MIN_VALUE,
    val max: Int = Int.MAX_VALUE,
    val required: Boolean = false
)
