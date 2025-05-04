package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.common.DSLBuilder

data class DeviceAttribute(
    val bool: Boolean? = null,
    val int: Long? = null,
    val string: String? = null,
    val version: String? = null
) {
    class Builder : DSLBuilder<DeviceAttribute> {
        private var bool: Boolean? = null
        var int: Long? = null
        var string: String? = null
        var version: String? = null

        fun bool(value: Boolean = true) {
            bool = value
        }

        override fun build(): DeviceAttribute {
            return DeviceAttribute(
                bool = bool,
                int = int,
                string = string,
                version = version
            )
        }
    }

    class MapGroup {
        private val map = mutableMapOf<String, DeviceAttribute>()

        fun add(key: String, scope: Builder.() -> Unit) {
            map[key] = Builder().apply(scope).build()
        }

        fun build(): Map<String, DeviceAttribute> {
            return map
        }
    }
}