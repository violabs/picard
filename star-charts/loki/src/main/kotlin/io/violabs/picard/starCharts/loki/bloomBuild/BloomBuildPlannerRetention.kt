package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class BloomBuildPlannerRetention(
    /**
     * # Enable bloom retention.
     * # CLI flag: -bloom-build.planner.retention.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null
)