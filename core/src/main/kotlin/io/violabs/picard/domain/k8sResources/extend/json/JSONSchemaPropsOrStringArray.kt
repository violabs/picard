package io.violabs.picard.domain.k8sResources.extend.json

data class JSONSchemaPropsOrStringArray(
    val props: JSONSchemaProps? = null,
    val array: List<String>? = null
) {
    class Builder {
        private var props: JSONSchemaProps? = null
        private var array: List<String>? = null

        fun props(block: JSONSchemaProps.Builder.() -> Unit) {
            this.props = JSONSchemaProps.Builder().apply(block).build()
        }

        fun array(vararg array: String) {
            this.array = array.toList()
        }

        fun build(): JSONSchemaPropsOrStringArray {
            return JSONSchemaPropsOrStringArray(
                props = props,
                array = array
            )
        }
    }

    class MapGroup {
        private val map = mutableMapOf<String, JSONSchemaPropsOrStringArray>()

        fun add(key: String, block: Builder.() -> Unit) {
            map[key] = Builder().apply(block).build()
        }

        fun build(): Map<String, JSONSchemaPropsOrStringArray> {
            return map
        }
    }
}