package io.violabs.picard.domain

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.APIVersion

data class ObjectReference(
    val apiVersion: APIVersion? = null,
    val fieldPath: String? = null,
    val kind: String? = null,
    val name: String? = null,
    val namespace: String? = null,
    val resourceVersion: String? = null,
    val uid: String? = null
) {
    class Builder : DslBuilder<ObjectReference> {
        var apiVersion: APIVersion? = null
        var fieldPath: String? = null
        var kind: String? = null
        var name: String? = null
        var namespace: String? = null
        var resourceVersion: String? = null
        var uid: String? = null

        override fun build(): ObjectReference {
            return ObjectReference(
                apiVersion = apiVersion,
                fieldPath = fieldPath,
                kind = kind,
                name = name,
                namespace = namespace,
                resourceVersion = resourceVersion,
                uid = uid
            )
        }
    }

    class Group : BuilderGroup<ObjectReference, Builder>(Builder()) {
        fun references(): List<ObjectReference>? = items()

        fun addObjectReference(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}