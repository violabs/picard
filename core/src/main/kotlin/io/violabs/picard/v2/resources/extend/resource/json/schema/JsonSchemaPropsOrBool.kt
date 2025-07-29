package io.violabs.picard.v2.resources.extend.resource.json.schema

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * JsonSchemaPropsOrBool represents JSONSchemaProps or a boolean value. 
 * Defaults to true for the boolean property.
 */
@GeneratedDsl
data class JsonSchemaPropsOrBool(
    /**
     * Schema properties
     */
    val schema: JsonSchemaProps? = null,
    /**
     * Boolean value
     */
    val allows: Boolean? = null
)