package io.violabs.picard.starCharts.loki.ingester

import io.violabs.picard.starCharts.loki.Duration

data class WAL(
    /**
    # Enable writing of ingested data into WAL.
     *   # CLI flag: -ingester.wal-enabled
     *   [enabled: <boolean> | default = true]
     */
    val enabled: Boolean? = null,
    /**
     *   # Directory where the WAL data is stored and/or recovered from.
     *   # CLI flag: -ingester.wal-dir
     *   [dir: <string> | default = "wal"]
     */
    val dir: String? = null,
    /**
     *   # Interval at which checkpoints should be created.
     *   # CLI flag: -ingester.checkpoint-duration
     *   [checkpoint_duration: <duration> | default = 5m]
     */
    val checkpointDuration: Duration? = null,
    /**
     *   # When WAL is enabled, should chunks be flushed to long-term storage on
     *   # shutdown.
     *   # CLI flag: -ingester.flush-on-shutdown
     *   [flush_on_shutdown: <boolean> | default = false]
     */
    val maxRetries: Int? = null,
    /**
     *   # Maximum memory size the WAL may use during replay. After hitting this, it
     *   # will flush data to storage before continuing. A unit suffix (KB, MB, GB) may
     *   # be applied.
     *   # CLI flag: -ingester.wal-replay-memory-ceiling
     *   [replay_memory_ceiling: <int> | default = 4GB]
     */
    val replayMemoryCeiling: Int? = null
)
