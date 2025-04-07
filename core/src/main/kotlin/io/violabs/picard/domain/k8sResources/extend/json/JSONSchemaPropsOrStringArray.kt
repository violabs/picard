package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrStringArray(
    val props: JSONSchemaProps? = null,
    val array: List<String>? = null
)