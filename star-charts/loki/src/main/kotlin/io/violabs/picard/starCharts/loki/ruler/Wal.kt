package io.violabs.picard.starCharts.loki.ruler

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class Wal(
    /**
     *   # The directory in which to write tenant WAL files. Each tenant will have its
     *   # own directory one level below this directory.
     *   # CLI flag: -ruler.wal.dir
     *   [dir: <string> | default = "ruler-wal"]
     */
    val dir: String? = null,
    /**
     *   # Frequency with which to run the WAL truncation process.
     *   # CLI flag: -ruler.wal.truncate-frequency
     *   [truncate_frequency: <duration> | default = 1h]
     */
    val truncateFrequency: Duration? = null,
    /**
     *   # Minimum age that samples must exist in the WAL before being truncated.
     *   # CLI flag: -ruler.wal.min-age
     *   [min_age: <duration> | default = 5m]
     */
    val minAge: Duration? = null,
    /**
     *   # Maximum age that samples must exist in the WAL before being truncated.
     *   # CLI flag: -ruler.wal.max-age
     *   [max_age: <duration> | default = 4h]
     */
    val maxAge: Duration? = null
)