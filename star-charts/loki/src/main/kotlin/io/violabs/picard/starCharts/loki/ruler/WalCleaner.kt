package io.violabs.picard.starCharts.loki.ruler

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class WalCleaner(
    /**
     *   # The minimum age of a WAL to consider for cleaning.
     *   # CLI flag: -ruler.wal-cleaner.min-age
     *   [min_age: <duration> | default = 12h]
     */
    val minAge: Duration? = null,
    /**
     *   # How often to run the WAL cleaner. 0 = disabled.
     *   # CLI flag: -ruler.wal-cleaner.period
     *   [period: <duration> | default = 0s]
     */
    val period: Duration? = null
)