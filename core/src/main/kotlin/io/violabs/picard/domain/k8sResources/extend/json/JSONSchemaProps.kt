package io.violabs.picard.domain.k8sResources.extend.json

import io.violabs.picard.domain.KeyTransform

data class JSONSchemaProps(
    val ref: String? = null,
    val schema: String? = null,
    val additionalItems: JSONSchemaPropsOrBool? = null,
    val allOf: List<JSONSchemaProps>? = null,
    val anyOf: List<JSONSchemaProps>? = null,
    val default: JSON? = null,
    val definitions: Map<String, JSONSchemaProps>? = null,
    val dependencies: Map<String, JSONSchemaPropsOrStringArray>? = null,
    val description: String? = null,
    val enum: List<JSON>? = null,
    val example: JSON? = null,
    val exclusiveMaximum: Boolean? = null,
    val exclusiveMinimum: Boolean? = null,
    val externalDocs: ExternalDocumentation? = null,
    val format: String? = null,
    val id: String? = null,
    val items: JSONSchemaPropsOrArray? = null,
    val maxItems: Long? = null,
    val maxLength: Long? = null,
    val maxProperties: Long? = null,
    val maximum: Double? = null,
    val minItems: Long? = null,
    val minLength: Long? = null,
    val minProperties: Long? = null,
    val minimum: Double? = null,
    val multipleOf: Double? = null,
    val not: JSONSchemaProps? = null,
    val nullable: Boolean? = null,
    val oneOf: List<JSONSchemaProps>? = null,
    val pattern: String? = null,
    val patternProperties: Map<String, JSONSchemaProps>? = null,
    val properties: Map<String, JSONSchemaProps>? = null,
    val required: List<String>? = null,
    val title: String? = null,
    val type: String? = null,
    val uniqueItems: Boolean? = null,
    @KeyTransform("x-kubernetes-embedded-resource")
    val xKubernetesEmbeddedResource: Boolean? = null,
    @KeyTransform("x-kubernetes-int-or-string")
    val xKubernetesIntOrString: Boolean? = null,
    @KeyTransform("x-kubernetes-list-map-keys")
    val xKubernetesListMapKeys: List<String>? = null,
    @KeyTransform("x-kubernetes-list-type")
    val xKubernetesListType: String? = null,
    @KeyTransform("x-kubernetes-map-type")
    val xKubernetesMapType: String? = null,
    @KeyTransform("x-kubernetes-preserve-unknown-fields")
    val xKubernetesPreserveUnknownFields: Boolean? = null,
    @KeyTransform("x-kubernetes-validations")
    val xKubernetesValidations: List<ValidationRule>? = null
)