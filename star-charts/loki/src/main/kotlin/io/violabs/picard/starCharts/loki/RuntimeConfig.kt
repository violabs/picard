package io.violabs.picard.starCharts.loki

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class RuntimeConfig(
    /**
     * # How often to check runtime config files.
     * # CLI flag: -runtime-config.reload-period
     * [period: <duration> | default = 10s]
     */
    val period: Duration? = null,
    /**
     * # Comma separated list of yaml files with the configuration that can be updated
     * # at runtime. Runtime config files will be merged from left to right.
     * # CLI flag: -runtime-config.file
     * [file: <string> | default = ""]
     */
    val file: String? = null,
)
