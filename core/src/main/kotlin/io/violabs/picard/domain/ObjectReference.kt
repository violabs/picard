package io.violabs.picard.domain

data class ObjectReference(
    val apiVersion: APIVersion? = null,
    val fieldPath: String? = null,
    val kind: Kind? = null,
    val name: String? = null,
    val namespace: String? = null,
    val resourceVersion: String? = null,
    val uid: String? = null
)