package io.violabs.picard.domain.manifest

import io.violabs.picard.domain.k8sResources.K8sAPIResource

interface ManifestResource {
    val resources: List<K8sAPIResource<*>>
}