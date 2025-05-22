package io.violabs.picard.starCharts.loki.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDsl
class Common(
    /**
     * # prefix for the path
     * # CLI flag: -common.path-prefix
     * [path_prefix: <string> | default = ""]
     */
    val pathPrefix: String? = null,
    val storage: Storage? = null,
    /**
     * [persist_tokens: <boolean>]
     */
    val persistTokens: Boolean? = null,
    /**
     * [replication_factor: <int>]
     */
    val replicationFactor: Int? = null,
    val ring: Ring? = null,
    /**
     * [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     */
    val instanceInterfaceNames: List<String>? = null,
    /**
     * [instance_addr: <string> | default = ""]
     */
    @JsonProperty("instanceAddr")
    val instanceAddress: String? = null,
    /**
     * # the http address of the compactor in the form http://host:port
     * # CLI flag: -common.compactor-address
     * [compactor_address: <string> | default = ""]
     */
    val compactorAddress: String? = null,
    /**
     * # the grpc address of the compactor in the form host:port
     * # CLI flag: -common.compactor-grpc-address
     * [compactor_grpc_address: <string> | default = ""]
     */
    val compactorGRPCAddress: String? = null
)