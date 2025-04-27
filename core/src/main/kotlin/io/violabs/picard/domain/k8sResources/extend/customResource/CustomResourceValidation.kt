package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.domain.k8sResources.extend.json.JSONSchemaProps

data class CustomResourceValidation(
    val openAPIV3Schema: JSONSchemaProps? = null
) {
    class Builder {
        private var openAPIV3Schema: JSONSchemaProps? = null

        fun openAPIV3Schema(block: JSONSchemaProps.Builder.() -> Unit) {
            this.openAPIV3Schema = JSONSchemaProps.Builder().apply(block).build()
        }

        fun build(): CustomResourceValidation {
            return CustomResourceValidation(
                openAPIV3Schema = openAPIV3Schema
            )
        }
    }
}