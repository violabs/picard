package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrArray(
    val props: JSONSchemaProps? = null,
    val array: List<Any>? = null
)