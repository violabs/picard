package io.violabs.picard.starCharts.loki.ring

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Consul
import io.violabs.picard.starCharts.loki.ETCD

@GeneratedDSL
data class KVStore(
    /**
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -index-gateway.ring.store
     *     [store: <string> | default = "consul"]
     */
    val store: String? = null,
    /**
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -index-gateway.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     */
    val prefix: String? = null,
    /**
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: index-gateway.ring
     *     [consul: <consul>]
     */
    val consul: Consul? = null,
    /**
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: index-gateway.ring
     *     [etcd: <etcd>]
     */
    val etcd: ETCD? = null,
    val multi: Multi? = null,
)