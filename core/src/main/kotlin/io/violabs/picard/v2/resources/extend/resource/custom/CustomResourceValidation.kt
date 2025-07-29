package io.violabs.picard.v2.resources.extend.resource.custom

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.extend.resource.json.schema.JsonSchemaProps

/**
 * CustomResourceValidation is a list of validation methods for CustomResources.
 */
@GeneratedDsl
data class CustomResourceValidation(
    /**
     * openAPIV3Schema is the OpenAPI v3 schema to use for validation and pruning.
     */
    @JsonProperty("openAPIV3Schema")
    val openApiV3Schema: JsonSchemaProps? = null
)