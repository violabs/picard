package io.violabs.picard.common

import java.util.*

fun <E : Enum<E>> enumSetOf(vararg values: E): EnumSet<E> {
    return EnumSet.copyOf(values.toMutableList())
}