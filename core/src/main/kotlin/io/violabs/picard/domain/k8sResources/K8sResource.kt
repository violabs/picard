package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.BaseK8s

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
}