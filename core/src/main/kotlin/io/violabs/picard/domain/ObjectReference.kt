package io.violabs.picard.domain

import io.violabs.picard.domain.k8sResources.APIVersion

data class ObjectReference(
    val apiVersion: APIVersion? = null,
    val fieldPath: String? = null,
    val kind: String? = null,
    val name: String? = null,
    val namespace: String? = null,
    val resourceVersion: String? = null,
    val uid: String? = null
)