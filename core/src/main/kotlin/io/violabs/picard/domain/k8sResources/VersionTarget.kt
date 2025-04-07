package io.violabs.picard.domain.k8sResources

import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class VersionTarget<T : APIVersion>(
    val version: KClass<T>,
    val required: Boolean = false
)
