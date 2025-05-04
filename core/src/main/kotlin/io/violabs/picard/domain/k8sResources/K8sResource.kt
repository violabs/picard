package io.violabs.picard.domain.k8sResources

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.*

interface K8sAPIResource<T : APIVersion> : BaseK8s {
    val apiVersion: T
}

/**
 * Kind is implied by the class name.
 */
interface K8sResource<T : APIVersion> : K8sAPIResource<T> {
    val metadata: ObjectMetadata?
}

interface K8sListResource<T : APIVersion, E> : K8sAPIResource<T> {
    val items: List<E>
    val metadata: ListMeta?

    open class ItemGroup<E, B : DSLBuilder<E>>(builder: B) : BuilderGroup<E, B>(builder) {
        fun listItems(): List<E>? = items()

        fun item(scope: B.() -> Unit) {
            add(scope)
        }
    }
}