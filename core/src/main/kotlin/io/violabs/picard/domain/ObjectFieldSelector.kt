package io.violabs.picard.domain

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.k8sResources.APIVersion

data class ObjectFieldSelector(
    val fieldPath: String,
    val apiVersion: APIVersion? = null
) {
    class Builder : DSLBuilder<ObjectFieldSelector> {
        var fieldPath: String? = null
        var apiVersion: APIVersion? = null
        override fun build(): ObjectFieldSelector {
            return ObjectFieldSelector(
                fieldPath = vRequireNotNull(this::fieldPath),
                apiVersion = apiVersion
            )
        }
    }
}