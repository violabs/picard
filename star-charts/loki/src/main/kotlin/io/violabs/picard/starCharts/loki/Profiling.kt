package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDsl

@GeneratedDsl
class Profiling(
    /**
     * # Sets the value for runtime.SetBlockProfilingRate
     * # CLI flag: -profiling.block-profile-rate
     * [block_profile_rate: <int> | default = 0]
     */
    val blockProfileRate: Int? = null,
    /**
     * # Sets the value for runtime.SetCPUProfileRate
     * # CLI flag: -profiling.cpu-profile-rate
     * [cpu_profile_rate: <int> | default = 0]
     */
    val cpuProfileRate: Int? = null,
    /**
     * # Sets the value for runtime.SetMutexProfileFraction
     * # CLI flag: -profiling.mutex-profile-fraction
     * [mutex_profile_fraction: <int> | default = 0]
     */
    val mutexProfileFraction: Int? = null,
)