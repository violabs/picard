package io.violabs.picard.starCharts.loki.common

data class FileSystem(
    /**
     * # Directory to store chunks in.
     * # CLI flag: -common.storage.filesystem.chunk-directory
     * [chunks_directory: <string> | default = ""]
     */
    val chunksDirectory: String? = null,
    /**
     * # Directory to store rules in.
     * # CLI flag: -common.storage.filesystem.rules-directory
     * [rules_directory: <string> | default = ""]
     */
    val rulesDirectory: String? = null
)