package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrBool(
    val props: JSONSchemaProps? = null,
    val bool: Boolean? = null
) {
    class Builder {
        private var props: JSONSchemaProps? = null
        private var bool: Boolean? = null

        fun props(block: JSONSchemaProps.Builder.() -> Unit) {
            this.props = JSONSchemaProps.Builder().apply(block).build()
        }

        fun bool(value: Boolean = true) {
            this.bool = value
        }

        fun build(): JSONSchemaPropsOrBool {
            return JSONSchemaPropsOrBool(
                props = props,
                bool = bool
            )
        }
    }
}