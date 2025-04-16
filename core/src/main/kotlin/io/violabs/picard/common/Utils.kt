package io.violabs.picard.common

import io.violabs.picard.domain.k8sResources.Quantity
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

/**
 * Takes in a classes property accessor and validates that is not null.
 * It used the accessor name as the default within the exception message.
 */
internal fun <T> vRequireNotNull(accessor: KProperty<T?>): T {
    accessor.isAccessible = true
    return requireNotNull(accessor.getter.call()) { "${accessor.name} is required" }
}

/**
 * Takes in a classes property accessor and validates that is not null or blank.
 * It used the accessor name as the default within the exception message.
 * You may find problems using with nested class functions.
 */
internal fun <T> vRequireNotEmpty(accessor: KProperty<List<T>?>): List<T> {
    accessor.isAccessible = true
    val accessorValue: List<T>? = accessor.call()

    val returnedValue = if (accessorValue?.isEmpty() != false) null else accessorValue

    return requireNotNull(returnedValue) { "${accessor.name} is required and cannot be empty" }
}

/**
 * Takes in a classes function accessor and validates that is not null or blank.
 * It used the accessor name as the default within the exception message.
 * You may find problems using with nested class functions.
 */
internal fun <T> vRequireNotEmpty(accessor: KFunction<List<T>?>): List<T> {
    accessor.isAccessible = true
    val accessorValue: List<T>? = accessor.call()

    val returnedValue = if (accessorValue?.isEmpty() != false) null else accessorValue

    return requireNotNull(returnedValue) { "${accessor.name} is required and cannot be empty" }
}

fun String.quantity(): Quantity = Quantity(this)