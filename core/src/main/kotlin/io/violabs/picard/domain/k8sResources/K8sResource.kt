package io.violabs.picard.domain.k8sResources

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.BaseK8s
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.serialization.KAPIVersionJsonSerializer

interface K8sAPIResource<T : APIVersion> : BaseK8s {
    @get:JsonSerialize(using = KAPIVersionJsonSerializer::class)
    val apiVersion: T

    fun getKind(): String = this::class.simpleName!!
}

/**
 * Kind is implied by the class name.
 */
@JsonPropertyOrder("apiVersion", "kind", "metadata", "spec", "status")
interface K8sResource<T : APIVersion, M> : K8sAPIResource<T> {
    val metadata: M?
}

@JsonPropertyOrder("apiVersion", "kind", "metadata", "items")
interface K8sListResource<T : APIVersion, E> : K8sAPIResource<T> {
    val items: List<E>
    val metadata: ListMeta?

    open class ItemGroup<E, B : DslBuilder<E>>(builder: B) : BuilderGroup<E, B>(builder) {
        fun listItems(): List<E>? = items()

        fun item(scope: B.() -> Unit) {
            add(scope)
        }
    }
}