package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * StatefulSetUpdateStrategy indicates the strategy that the StatefulSet
 * controller will use to perform updates. It includes any additional parameters necessary
 * to perform the update for the indicated strategy.
 */
@GeneratedDsl
data class StatefulSetUpdateStrategy(
    /**
     * Type indicates the type of the StatefulSetUpdateStrategy. Default is RollingUpdate.
     */
    val type: String? = null,
    /**
     * RollingUpdate is used to communicate parameters when Type is RollingUpdateStatefulSetStrategyType.
     */
    val rollingUpdate: RollingUpdateStatefulSetStrategy? = null
)