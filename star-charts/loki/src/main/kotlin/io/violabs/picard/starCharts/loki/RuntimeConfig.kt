package io.violabs.picard.starCharts.loki

class RuntimeConfig(
    /**
     * # How often to check runtime config files.
     * # CLI flag: -runtime-config.reload-period
     * [period: <duration> | default = 10s]
     *
     * # Comma separated list of yaml files with the configuration that can be updated
     * # at runtime. Runtime config files will be merged from left to right.
     * # CLI flag: -runtime-config.file
     * [file: <string> | default = ""]
     */
) {
}