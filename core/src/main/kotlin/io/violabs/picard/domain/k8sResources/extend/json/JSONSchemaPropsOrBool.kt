package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrBool(
    val props: JSONSchemaProps? = null,
    val bool: Boolean? = null
)