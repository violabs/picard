package io.violabs.picard.starCharts.loki.limitsConfig

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL
data class ShardStreams(
    /**
     *   # Automatically shard streams to keep them under the per-stream rate limit.
     *   # Sharding is dictated by the desired rate.
     *   # CLI flag: -shard-streams.enabled
     *   [enabled: <boolean> | default = true]
     */
    val enabled: Boolean? = null,
    /**
     *   # Automatically shard streams by adding a __time_shard__ label, with values
     *   # calculated from the log timestamps divided by MaxChunkAge/2. This allows the
     *   # out-of-order ingestion of very old logs. If both flags are enabled,
     *   # time-based sharding will happen before rate-based sharding.
     *   # CLI flag: -shard-streams.time-sharding-enabled
     *   [time_sharding_enabled: <boolean> | default = false]
     */
    val timeShardingEnabled: Boolean? = null,
    /**
     *   # Logs with timestamps that are newer than this value will not be
     *   # time-sharded.
     *   # CLI flag: -shard-streams.time-sharding-ignore-recent
     *   [time_sharding_ignore_recent: <duration> | default = 40m]
     */
    val timeShardingIgnoreRecent: Duration? = null,
    /**
     *   # Whether to log sharding streams behavior or not. Not recommended for
     *   # production environments.
     *   # CLI flag: -shard-streams.logging-enabled
     *   [logging_enabled: <boolean> | default = false]
     */
    val loggingEnabled: Boolean? = null,
    /**
     *   # Threshold used to cut a new shard. Default (1536KB) means if a rate is above
     *   # 1536KB/s, it will be sharded into two streams.
     *   # CLI flag: -shard-streams.desired-rate
     *   [desired_rate: <int> | default = 1536KB]
     */
    val desiredRate: Int? = null
)