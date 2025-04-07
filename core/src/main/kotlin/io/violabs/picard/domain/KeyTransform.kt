package io.violabs.picard.domain

/**
 * Used to transform the key on yaml generation. The value
 * will replace the name of the key.
 */
@MustBeDocumented
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class KeyTransform(
    val value: String
)