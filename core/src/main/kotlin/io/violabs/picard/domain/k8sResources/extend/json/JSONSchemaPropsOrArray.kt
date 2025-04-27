package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrArray(
    val props: JSONSchemaProps? = null,
    val array: List<Any>? = null
) {
    class Builder {
        private var props: JSONSchemaProps? = null
        private var array: List<Any>? = null

        fun props(block: JSONSchemaProps.Builder.() -> Unit) {
            this.props = JSONSchemaProps.Builder().apply(block).build()
        }

        fun array(vararg array: Any) {
            this.array = array.toList()
        }

        fun build(): JSONSchemaPropsOrArray {
            return JSONSchemaPropsOrArray(
                props = props,
                array = array
            )
        }
    }
}