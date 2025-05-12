package io.violabs.picard.dsl.annotation

import kotlin.reflect.KClass

/**
 * Used to let the DSL Generator create a function with a primitive or
 * other object as the input. Incompatible types will need to be handled.
 * Requires a single constructor with the expected returnType.
 * @property paramType the type of the input parameter
 * @property returnType the type the will assign to the constructor of the object
 */
annotation class SingleEntryDSL<T : Any, R : Any>(
    val paramType: KClass<T>,
    val returnType: KClass<R>
)