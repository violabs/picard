package io.violabs.picard.starCharts.loki.bloomBuild

data class BloomBuildPlannerRetention(
    /**
     * # Enable bloom retention.
     * # CLI flag: -bloom-build.planner.retention.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null
)