package io.violabs.picard.starCharts.loki.tableManager

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class TablesProvisioning(
    /**
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.index-table.enable-ondemand-throughput-mode
     *   [enable_ondemand_throughput_mode: <boolean> | default = false]
     */
    @JsonProperty("enableOndemandThroughputMode")
    val enableOnDemandThroughputMode: Boolean? = null,
    /**
     *   # Table default write throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.write-throughput
     *   [provisioned_write_throughput: <int> | default = 1000]
     */
    val provisionedWriteThroughput: Int? = null,
    /**
     *   # Table default read throughput. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.read-throughput
     *   [provisioned_read_throughput: <int> | default = 300]
     */
    val provisionedReadThroughput: Int? = null,
    val writeScale: Scale? = null,
    val readScale: Scale? = null,
    /**
     *   # Enables on demand throughput provisioning for the storage provider (if
     *   # supported). Applies only to tables which are not autoscaled. Supported by
     *   # DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-enable-ondemand-throughput-mode
     *   [enable_inactive_throughput_on_demand_mode: <boolean> | default = false]
     */
    val enableInactiveThroughputOnDemandMode: Boolean? = null,
    /**
     *   # Table write throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-write-throughput
     *   [inactive_write_throughput: <int> | default = 1]
     */
    val inactiveWriteThroughput: Int? = null,
    /**
     *   # Table read throughput for inactive tables. Supported by DynamoDB
     *   # CLI flag: -table-manager.index-table.inactive-read-throughput
     *   [inactive_read_throughput: <int> | default = 300]
     */
    val inactiveReadThroughput: Int? = null,
    val inactiveWriteScale: Scale? = null,
    val inactiveReadScale: Scale? = null,
    /**
     *   # Number of last inactive tables to enable write autoscale.
     *   # CLI flag: -table-manager.index-table.inactive-write-throughput.scale-last-n
     *   [inactive_write_scale_lastn: <int> | default = 4]
     */
    @JsonProperty("inactiveWriteScaleLastn")
    val inactiveWriteScaleLastN: Int? = null,
    /**
     *   # Number of last inactive tables to enable read autoscale.
     *   # CLI flag: -table-manager.index-table.inactive-read-throughput.scale-last-n
     *   [inactive_read_scale_lastn: <int> | default = 4]
     */
    @JsonProperty("inactiveReadScaleLastn")
    val inactiveReadScaleLastN: Int? = null
)