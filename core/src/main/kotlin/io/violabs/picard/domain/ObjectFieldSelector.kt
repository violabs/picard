package io.violabs.picard.domain

import io.violabs.picard.domain.k8sResources.APIVersion

data class ObjectFieldSelector(
    val fieldPath: String,
    val apiVersion: APIVersion? = null
)