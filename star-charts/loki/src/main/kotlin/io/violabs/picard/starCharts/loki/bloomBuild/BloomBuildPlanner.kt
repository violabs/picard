package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
class BloomBuildPlanner(
    /**
     * # Interval at which to re-run the bloom creation planning.
     * # CLI flag: -bloom-build.planner.interval
     * [planning_interval: <duration> | default = 8h]
     */
    val planningInterval: Duration? = null,
    /**
     *   # Newest day-table offset (from today, inclusive) to build blooms for. 0 start
     *   # building from today, 1 from yesterday and so on. Increase to lower cost by
     *   # not re-writing data to object storage too frequently since recent data
     *   # changes more often at the cost of not having blooms available as quickly.
     *   # CLI flag: -bloom-build.planner.min-table-offset
     *   [min_table_offset: <int> | default = 0]
     */
    val minTableOffset: Int? = null,
    /**
     *   # Oldest day-table offset (from today, inclusive) to build blooms for. 1 till
     *   # yesterday, 2 till day before yesterday and so on. This can be used to lower
     *   # cost by not trying to build blooms for older data which doesn't change. This
     *   # can be optimized by aligning it with the maximum
     *   # `reject_old_samples_max_age` setting of any tenant.
     *   # CLI flag: -bloom-build.planner.max-table-offset
     *   [max_table_offset: <int> | default = 1]
     */
    val maxTableOffset: Int? = null,
    val retention: BloomBuildPlannerRetention? = null,
    val queue: BloomBuildPlannerQueue? = null
)