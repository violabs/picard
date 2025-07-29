package io.violabs.picard.v2.resources.extend.resource.json.schema

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * JsonSchemaPropsOrArray represents a value that can either be a JSONSchemaProps or an array of JSONSchemaProps. 
 * Mainly here for serialization purposes.
 */
@GeneratedDsl
data class JsonSchemaPropsOrArray(
    /**
     * Single schema
     */
    val schema: JsonSchemaProps? = null,
    /**
     * Array of schemas
     */
    val schemas: List<JsonSchemaProps>? = null
)