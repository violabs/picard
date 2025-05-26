package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class FileSystem(
    /**
     *   # Local filesystem storage directory.
     *   # CLI flag: -<prefix>.filesystem.dir
     *   [dir: <string> | default = ""]
     */
    val dir: String? = null,
)