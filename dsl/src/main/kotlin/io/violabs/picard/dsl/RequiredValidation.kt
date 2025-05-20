package io.violabs.picard.dsl

import io.violabs.picard.common.ExcludeFromCoverage
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

/**
 * Ensures the value returned by the given property reference is not `null`.
 *
 * @param accessor the property reference being validated
 * @return the non-null value of [accessor]
 */
@ExcludeFromCoverage
fun <T> vRequireNotNull(accessor: KProperty<T?>): T {
    accessor.isAccessible = true
    return requireNotNull(accessor.getter.call()) { "${accessor.name} is required" }
}

/**
 * Common implementation for [vRequireNotEmpty] variants.
 */
@ExcludeFromCoverage
private fun <T> requireNotEmptyInternal(value: List<T>?, name: String): List<T> {
    val returnedValue = if (value?.isEmpty() != false) null else value
    return requireNotNull(returnedValue) { "$name is required and cannot be empty" }
}

/**
 * Validates that the supplied list is not null or empty.
 */
@ExcludeFromCoverage
fun <T> vRequireNotEmpty(value: List<T>?, name: String): List<T> =
    requireNotEmptyInternal(value, name)

/**
 * Takes in a classes property accessor and validates that is not null or blank.
 * It used the accessor name as the default within the exception message.
 * You may find problems using with nested class functions.
 */
@ExcludeFromCoverage
fun <T> vRequireNotEmpty(accessor: KProperty<List<T>?>): List<T> {
    accessor.isAccessible = true
    return requireNotEmptyInternal(accessor.call(), accessor.name)
}

/**
 * Takes in a classes function accessor and validates that is not null or blank.
 * It used the accessor name as the default within the exception message.
 * You may find problems using with nested class functions.
 */
@ExcludeFromCoverage
fun <T> vRequireNotEmpty(accessor: KFunction<List<T>?>): List<T> {
    accessor.isAccessible = true
    return requireNotEmptyInternal(accessor.call(), accessor.name)
}