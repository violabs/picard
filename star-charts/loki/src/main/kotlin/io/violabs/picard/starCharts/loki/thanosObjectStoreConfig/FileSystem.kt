package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class FileSystem(
    /**
     *   # Local filesystem storage directory.
     *   # CLI flag: -<prefix>.filesystem.dir
     *   [dir: <string> | default = ""]
     */
    val dir: String? = null,
)