package io.violabs.picard.domain.k8sResources.policy.limitRange

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class LimitRangeItem(
    val type: String,
    val default: Map<String, Quantity>? = null,
    val defaultRequest: Map<String, Quantity>? = null,
    val max: Map<String, Quantity>? = null,
    val maxLimitRequestRatio: Map<String, Quantity>? = null,
    val min: Map<String, Quantity>? = null
) {
    class Builder : DslBuilder<LimitRangeItem> {
        var type: String? = null
        private var default: Map<String, Quantity>? = null
        private var defaultRequest: Map<String, Quantity>? = null
        private var max: Map<String, Quantity>? = null
        private var maxLimitRequestRatio: Map<String, Quantity>? = null
        private var min: Map<String, Quantity>? = null

        fun default(vararg default: Pair<String, Quantity>) {
            this.default = default.toMap()
        }

        fun defaultRequest(vararg defaultRequest: Pair<String, Quantity>) {
            this.defaultRequest = defaultRequest.toMap()
        }

        fun max(vararg max: Pair<String, Quantity>) {
            this.max = max.toMap()
        }

        fun maxLimitRequestRatio(vararg maxLimitRequestRatio: Pair<String, Quantity>) {
            this.maxLimitRequestRatio = maxLimitRequestRatio.toMap()
        }

        fun min(vararg min: Pair<String, Quantity>) {
            this.min = min.toMap()
        }

        override fun build(): LimitRangeItem {
            return LimitRangeItem(
                type = vRequireNotNull(this::type),
                default = default,
                defaultRequest = defaultRequest,
                max = max,
                maxLimitRequestRatio = maxLimitRequestRatio,
                min = min
            )
        }
    }

    class Group : BuilderGroup<LimitRangeItem, Builder>(Builder()) {
        fun limitRangeItems(): List<LimitRangeItem>? = items()

        fun addLimitRangeItem(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}