package io.violabs.picard.domain

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class DeviceSelector(
    val cel: CEL? = null
) {
    data class CEL(
        val expression: String
    )

    class Builder : DslBuilder<DeviceSelector> {
        private var cel: CEL? = null

        fun cel(expression: String) {
            this.cel = CEL(expression)
        }

        override fun build(): DeviceSelector {
            return DeviceSelector(
                cel = cel
            )
        }
    }

    class Group : BuilderGroup<DeviceSelector, Builder>(Builder()) {
        fun selectors(): List<DeviceSelector>? = items()

        fun addDeviceSelector(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}