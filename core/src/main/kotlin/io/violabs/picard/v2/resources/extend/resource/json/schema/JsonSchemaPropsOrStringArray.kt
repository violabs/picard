package io.violabs.picard.v2.resources.extend.resource.json.schema

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class JsonSchemaPropsOrStringArray(
    val props: JsonSchemaProps? = null,
    val array: List<String>? = null
)