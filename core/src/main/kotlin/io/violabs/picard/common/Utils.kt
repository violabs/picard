package io.violabs.picard.common

import kotlin.reflect.KProperty

/**
 * Takes in a classes property accessor and validates that is not null.
 * It used the accessor name as the default within the exception message.
 */
internal fun <T> vRequireNotNull(accessor: KProperty<T?>): T {
    return requireNotNull(accessor.getter.call()) { "${accessor.name} is required" }
}