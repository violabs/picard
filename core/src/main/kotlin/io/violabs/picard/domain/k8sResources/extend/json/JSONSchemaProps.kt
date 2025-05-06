package io.violabs.picard.domain.k8sResources.extend.json

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
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
) {
    class Builder : DSLBuilder<JSONSchemaProps> {
        var ref: String? = null
        var schema: String? = null
        private var additionalItems: JSONSchemaPropsOrBool? = null
        private var allOf: List<JSONSchemaProps>? = null
        private var anyOf: List<JSONSchemaProps>? = null
        private var default: JSON? = null
        private var definitions: Map<String, JSONSchemaProps>? = null
        private var dependencies: Map<String, JSONSchemaPropsOrStringArray>? = null
        var description: String? = null
        private var enum: List<JSON>? = null
        private var example: JSON? = null
        private var exclusiveMaximum: Boolean? = null
        private var exclusiveMinimum: Boolean? = null
        private var externalDocs: ExternalDocumentation? = null
        var format: String? = null
        var id: String? = null
        private var items: JSONSchemaPropsOrArray? = null
        var maxItems: Long? = null
        var maxLength: Long? = null
        var maxProperties: Long? = null
        var maximum: Double? = null
        var minItems: Long? = null
        var minLength: Long? = null
        var minProperties: Long? = null
        var minimum: Double? = null
        var multipleOf: Double? = null
        private var not: JSONSchemaProps? = null
        private var nullable: Boolean? = null
        private var oneOf: List<JSONSchemaProps>? = null
        var pattern: String? = null
        private var patternProperties: Map<String, JSONSchemaProps>? = null
        private var properties: Map<String, JSONSchemaProps>? = null
        private var required: List<String>? = null
        var title: String? = null
        var type: String? = null
        private var uniqueItems: Boolean? = null
        private var xKubernetesEmbeddedResource: Boolean? = null
        private var xKubernetesIntOrString: Boolean? = null
        private var xKubernetesListMapKeys: List<String>? = null
        var xKubernetesListType: String? = null
        var xKubernetesMapType: String? = null
        private var xKubernetesPreserveUnknownFields: Boolean? = null
        private var xKubernetesValidations: List<ValidationRule>? = null

        fun additionalItems(scope: JSONSchemaPropsOrBool.Builder.() -> Unit) {
            this.additionalItems = JSONSchemaPropsOrBool.Builder().apply(scope).build()
        }

        fun allOf(scope: Group.() -> Unit) {
            this.allOf = Group().apply(scope).addJSONSchemaProps()
        }

        fun anyOf(scope: Group.() -> Unit) {
            this.anyOf = Group().apply(scope).addJSONSchemaProps()
        }

        fun default(jsonContent: String) {
            this.default = JSON(jsonContent)
        }

        fun definitions(scope: MapGroup.() -> Unit) {
            this.definitions = MapGroup().apply(scope).build()
        }

        fun dependencies(scope: JSONSchemaPropsOrStringArray.MapGroup.() -> Unit) {
            this.dependencies = JSONSchemaPropsOrStringArray.MapGroup().apply(scope).build()
        }

        fun enum(vararg jsonContent: String) {
            this.enum = jsonContent.map { JSON(it) }
        }

        fun example(jsonContent: String) {
            this.example = JSON(jsonContent)
        }

        fun exclusiveMaximum(value: Boolean = true) {
            this.exclusiveMaximum = value
        }

        fun exclusiveMinimum(value: Boolean = true) {
            this.exclusiveMinimum = value
        }

        fun externalDocs(scope: ExternalDocumentation.Builder.() -> Unit) {
            this.externalDocs = ExternalDocumentation.Builder().apply(scope).build()
        }

        fun items(scope: JSONSchemaPropsOrArray.Builder.() -> Unit) {
            this.items = JSONSchemaPropsOrArray.Builder().apply(scope).build()
        }

        fun not(scope: Builder.() -> Unit) {
            this.not = Builder().apply(scope).build()
        }

        fun nullable(value: Boolean = true) {
            this.nullable = value
        }

        fun oneOf(scope: Group.() -> Unit) {
            this.oneOf = Group().apply(scope).addJSONSchemaProps()
        }

        fun patternProperties(scope: MapGroup.() -> Unit) {
            this.patternProperties = MapGroup().apply(scope).build()
        }

        fun properties(scope: MapGroup.() -> Unit) {
            this.properties = MapGroup().apply(scope).build()
        }

        fun required(vararg required: String) {
            this.required = required.toList()
        }

        fun uniqueItems(value: Boolean = true) {
            this.uniqueItems = value
        }

        fun xKubernetesEmbeddedResource(value: Boolean = true) {
            this.xKubernetesEmbeddedResource = value
        }

        fun xKubernetesIntOrString(value: Boolean = true) {
            this.xKubernetesIntOrString = value
        }

        fun xKubernetesListMapKeys(vararg keys: String) {
            this.xKubernetesListMapKeys = keys.toList()
        }

        fun xKubernetesPreserveUnknownFields(value: Boolean = true) {
            this.xKubernetesPreserveUnknownFields = value
        }

        fun xKubernetesValidations(scope: ValidationRule.Group.() -> Unit) {
            this.xKubernetesValidations = ValidationRule.Group().apply(scope).rules()
        }

        override fun build(): JSONSchemaProps {
            return JSONSchemaProps(
                ref = ref,
                schema = schema,
                additionalItems = additionalItems,
                allOf = allOf,
                anyOf = anyOf,
                default = default,
                definitions = definitions,
                dependencies = dependencies,
                description = description,
                enum = enum,
                example = example,
                exclusiveMaximum = exclusiveMaximum,
                exclusiveMinimum = exclusiveMinimum,
                externalDocs = externalDocs,
                format = format,
                id = id,
                items = items,
                maxItems = maxItems,
                maxLength = maxLength,
                maxProperties = maxProperties,
                maximum = maximum,
                minItems = minItems,
                minLength = minLength,
                minProperties = minProperties,
                minimum = minimum,
                multipleOf = multipleOf,
                not = not,
                nullable = nullable,
                oneOf = oneOf,
                pattern = pattern,
                patternProperties = patternProperties,
                properties = properties,
                required = required,
                title = title,
                type = type,
                uniqueItems = uniqueItems,
                xKubernetesEmbeddedResource = xKubernetesEmbeddedResource,
                xKubernetesIntOrString = xKubernetesIntOrString,
                xKubernetesListMapKeys = xKubernetesListMapKeys,
                xKubernetesListType = xKubernetesListType,
                xKubernetesMapType = xKubernetesMapType,
                xKubernetesPreserveUnknownFields = xKubernetesPreserveUnknownFields,
                xKubernetesValidations = xKubernetesValidations
            )
        }
    }

    class Group : BuilderGroup<JSONSchemaProps, Builder>(Builder()) {
        fun addJSONSchemaProps(): List<JSONSchemaProps>? = items()

        fun addJSONSchemaProps(scope: Builder.() -> Unit) {
            add(scope)
        }
    }

    class MapGroup {
        private val map = mutableMapOf<String, JSONSchemaProps>()

        fun add(key: String, scope: Builder.() -> Unit) {
            map[key] = Builder().apply(scope).build()
        }

        fun build(): Map<String, JSONSchemaProps> {
            return map
        }
    }
}