package io.violabs.picard.v2.resources.extend.resource.json.schema

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.extend.json.JSONSchemaPropsOrStringArray

/**
 * JsonSchemaProps is a JSON-Schema following Specification Draft 4 (http://json-schema.org/).
 * This is a simplified version focusing on commonly used fields.
 */
@GeneratedDsl
data class JsonSchemaProps(
    /**
     * JSON reference
     */
    @JsonProperty("\$ref")
    val ref: String? = null,
    /**
     * JSON schema version identifier
     */
    @JsonProperty("\$schema")
    val schema: String? = null,
    /**
     * Additional items schema
     */
    val additionalItems: JsonSchemaPropsOrBool? = null,
    /**
     * Additional properties schema
     */
    val additionalProperties: JsonSchemaPropsOrBool? = null,
    /**
     * All of validation
     */
    val allOf: List<JsonSchemaProps>? = null,
    /**
     * Any of validation
     */
    val anyOf: List<JsonSchemaProps>? = null,
    /**
     * Default value for the field
     */
    val default: String? = null,
    /**
     * Definitions for reusable schemas
     */
    val definitions: Map<String, JsonSchemaProps>? = null,
    /**
     * Dependencies validation
     */
    val dependencies: Map<String, JSONSchemaPropsOrStringArray>? = null,
    /**
     * Description of the field
     */
    val description: String? = null,
    /**
     * Enum values
     */
    val enum: List<String>? = null,
    /**
     * Example value
     */
    val example: String? = null,
    /**
     * Exclusive maximum value
     */
    val exclusiveMaximum: Boolean? = null,
    /**
     * Exclusive minimum value
     */
    val exclusiveMinimum: Boolean? = null,
    /**
     * External documentation
     */
    val externalDocs: ExternalDocumentation? = null,
    /**
     * Format specification for the field
     */
    val format: String? = null,
    /**
     * ID of the schema
     */
    val id: String? = null,
    /**
     * Items schema for arrays
     */
    val items: JsonSchemaPropsOrArray? = null,
    /**
     * Maximum number of items in array
     */
    val maxItems: Long? = null,
    /**
     * Maximum length of string
     */
    val maxLength: Long? = null,
    /**
     * Maximum number of properties in object
     */
    val maxProperties: Long? = null,
    /**
     * Maximum numeric value
     */
    val maximum: Double? = null,
    /**
     * Minimum number of items in array
     */
    val minItems: Long? = null,
    /**
     * Minimum length of string
     */
    val minLength: Long? = null,
    /**
     * Minimum number of properties in object
     */
    val minProperties: Long? = null,
    /**
     * Minimum numeric value
     */
    val minimum: Double? = null,
    /**
     * Multiple of validation for numbers
     */
    val multipleOf: Double? = null,
    /**
     * Not validation
     */
    val not: JsonSchemaProps? = null,
    /**
     * Nullable field indicator
     */
    val nullable: Boolean? = null,
    /**
     * One of validation
     */
    val oneOf: List<JsonSchemaProps>? = null,
    /**
     * Pattern validation for strings
     */
    val pattern: String? = null,
    /**
     * Pattern properties validation
     */
    val patternProperties: Map<String, JsonSchemaProps>? = null,
    /**
     * Object properties schemas
     */
    val properties: Map<String, JsonSchemaProps>? = null,
    /**
     * Required properties list
     */
    val required: List<String>? = null,
    /**
     * Title of the schema
     */
    val title: String? = null,
    /**
     * Type of the field
     */
    val type: String? = null,
    /**
     * Unique items validation for arrays
     */
    val uniqueItems: Boolean? = null,
    /**
     * Kubernetes embedded resource indicator
     */
    @JsonProperty("x-kubernetes-embedded-resource")
    val xKubernetesEmbeddedResource: Boolean? = null,
    /**
     * Kubernetes int-or-string indicator
     */
    @JsonProperty("x-kubernetes-int-or-string")
    val xKubernetesIntOrString: Boolean? = null,
    /**
     * Kubernetes list map keys
     */
    @JsonProperty("x-kubernetes-list-map-keys")
    val xKubernetesListMapKeys: List<String>? = null,
    /**
     * Kubernetes list type
     */
    @JsonProperty("x-kubernetes-list-type")
    val xKubernetesListType: String? = null,
    /**
     * Kubernetes map type
     */
    @JsonProperty("x-kubernetes-map-type")
    val xKubernetesMapType: String? = null,
    /**
     * Kubernetes preserve unknown fields
     */
    @JsonProperty("x-kubernetes-preserve-unknown-fields")
    val xKubernetesPreserveUnknownFields: Boolean? = null,
    /**
     * Kubernetes validations using CEL
     */
    @JsonProperty("x-kubernetes-validations")
    val xKubernetesValidations: List<ValidationRule>? = null
)