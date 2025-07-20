package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.common.DslBuilder

data class HorizontalPodAutoscalerBehavior(
    val scaleDown: HPAScalingRules? = null,
    val scaleUp: HPAScalingRules? = null
) {
    class Builder : DslBuilder<HorizontalPodAutoscalerBehavior> {
        private var scaleDown: HPAScalingRules? = null
        private var scaleUp: HPAScalingRules? = null

        fun scaleDown(block: HPAScalingRules.Builder.() -> Unit) {
            scaleDown = HPAScalingRules.Builder().apply(block).build()
        }

        fun scaleUp(block: HPAScalingRules.Builder.() -> Unit) {
            scaleUp = HPAScalingRules.Builder().apply(block).build()
        }

        override fun build(): HorizontalPodAutoscalerBehavior {
            return HorizontalPodAutoscalerBehavior(
                scaleDown = scaleDown,
                scaleUp = scaleUp
            )
        }
    }
}