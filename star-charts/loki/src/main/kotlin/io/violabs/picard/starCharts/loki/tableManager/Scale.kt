package io.violabs.picard.starCharts.loki.tableManager

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Scale(
    /**
     * # Should we enable autoscale for the table.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.enabled
     * [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    /**
     * # AWS AutoScaling role ARN
     * # CLI flag: -table-manager.index-table.write-throughput.scale.role-arn
     * [role_arn: <string> | default = ""]
     */
    val roleArn: String? = null,
    /**
     * # DynamoDB minimum provision capacity.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.min-capacity
     * [min_capacity: <int> | default = 3000]
     */
    val minCapacity: Int? = null,
    /**
     * # DynamoDB maximum provision capacity.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.max-capacity
     * [max_capacity: <int> | default = 6000]
     */
    val maxCapacity: Int? = null,
    /**
     * # DynamoDB minimum seconds between each autoscale up.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.out-cooldown
     * [out_cooldown: <int> | default = 1800]
     */
    val outCooldown: Int? = null,
    /**
     * # DynamoDB minimum seconds between each autoscale down.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.in-cooldown
     * [in_cooldown: <int> | default = 1800]
     */
    val inCooldown: Int? = null,
    /**
     * # DynamoDB target ratio of consumed capacity to provisioned capacity.
     * # CLI flag: -table-manager.index-table.write-throughput.scale.target-value
     * [target: <float> | default = 80]
     */
    val target: Float? = null
)