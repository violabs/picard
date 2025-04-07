package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.domain.k8sResources.extend.json.JSONSchemaProps

data class CustomResourceValidation(
    val openAPIV3Schema: JSONSchemaProps? = null
)