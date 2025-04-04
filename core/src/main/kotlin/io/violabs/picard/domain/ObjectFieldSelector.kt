package io.violabs.picard.domain

data class ObjectFieldSelector(
    val fieldPath: String,
    val apiVersion: APIVersion? = null
)