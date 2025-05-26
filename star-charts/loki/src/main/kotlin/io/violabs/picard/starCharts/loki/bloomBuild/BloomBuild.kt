package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class BloomBuild(
    /**
     * # Flag to enable or disable the usage of the bloom-planner and bloom-builder
     * # components.
     * # CLI flag: -bloom-build.enabled
     * [enabled: <boolean> | default = false]
     *
     */
    val enabled: Boolean? = null,
    val planner: BloomBuildPlanner? = null,
    val builder: BloomBuildBuilder? = null,
)