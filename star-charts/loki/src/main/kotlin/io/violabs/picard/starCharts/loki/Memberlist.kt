package io.violabs.picard.starCharts.loki

class Memberlist(
    /**
     * # Name of the node in memberlist cluster. Defaults to hostname.
     * # CLI flag: -memberlist.nodename
     * [node_name: <string> | default = ""]
     *
     * # Add random suffix to the node name.
     * # CLI flag: -memberlist.randomize-node-name
     * [randomize_node_name: <boolean> | default = true]
     *
     * # The timeout for establishing a connection with a remote node, and for
     * # read/write operations.
     * # CLI flag: -memberlist.stream-timeout
     * [stream_timeout: <duration> | default = 2s]
     *
     * # Multiplication factor used when sending out messages (factor * log(N+1)).
     * # CLI flag: -memberlist.retransmit-factor
     * [retransmit_factor: <int> | default = 4]
     *
     * # How often to use pull/push sync.
     * # CLI flag: -memberlist.pullpush-interval
     * [pull_push_interval: <duration> | default = 30s]
     *
     * # How often to gossip.
     * # CLI flag: -memberlist.gossip-interval
     * [gossip_interval: <duration> | default = 200ms]
     *
     * # How many nodes to gossip to.
     * # CLI flag: -memberlist.gossip-nodes
     * [gossip_nodes: <int> | default = 3]
     *
     * # How long to keep gossiping to dead nodes, to give them chance to refute their
     * # death.
     * # CLI flag: -memberlist.gossip-to-dead-nodes-time
     * [gossip_to_dead_nodes_time: <duration> | default = 30s]
     *
     * # How soon can dead node's name be reclaimed with new address. 0 to disable.
     * # CLI flag: -memberlist.dead-node-reclaim-time
     * [dead_node_reclaim_time: <duration> | default = 0s]
     *
     * # Enable message compression. This can be used to reduce bandwidth usage at the
     * # cost of slightly more CPU utilization.
     * # CLI flag: -memberlist.compression-enabled
     * [compression_enabled: <boolean> | default = true]
     *
     * # How frequently to notify watchers when a key changes. Can reduce CPU activity
     * # in large memberlist deployments. 0 to notify without delay.
     * # CLI flag: -memberlist.notify-interval
     * [notify_interval: <duration> | default = 0s]
     *
     * # Gossip address to advertise to other members in the cluster. Used for NAT
     * # traversal.
     * # CLI flag: -memberlist.advertise-addr
     * [advertise_addr: <string> | default = ""]
     *
     * # Gossip port to advertise to other members in the cluster. Used for NAT
     * # traversal.
     * # CLI flag: -memberlist.advertise-port
     * [advertise_port: <int> | default = 7946]
     *
     * # The cluster label is an optional string to include in outbound packets and
     * # gossip streams. Other members in the memberlist cluster will discard any
     * # message whose label doesn't match the configured one, unless the
     * # 'cluster-label-verification-disabled' configuration option is set to true.
     * # CLI flag: -memberlist.cluster-label
     * [cluster_label: <string> | default = ""]
     *
     * # When true, memberlist doesn't verify that inbound packets and gossip streams
     * # have the cluster label matching the configured one. This verification should
     * # be disabled while rolling out the change to the configured cluster label in a
     * # live memberlist cluster.
     * # CLI flag: -memberlist.cluster-label-verification-disabled
     * [cluster_label_verification_disabled: <boolean> | default = false]
     *
     * # Other cluster members to join. Can be specified multiple times. It can be an
     * # IP, hostname or an entry specified in the DNS Service Discovery format.
     * # CLI flag: -memberlist.join
     * [join_members: <list of strings> | default = []]
     *
     * # Min backoff duration to join other cluster members.
     * # CLI flag: -memberlist.min-join-backoff
     * [min_join_backoff: <duration> | default = 1s]
     *
     * # Max backoff duration to join other cluster members.
     * # CLI flag: -memberlist.max-join-backoff
     * [max_join_backoff: <duration> | default = 1m]
     *
     * # Max number of retries to join other cluster members.
     * # CLI flag: -memberlist.max-join-retries
     * [max_join_retries: <int> | default = 10]
     *
     * # If this node fails to join memberlist cluster, abort.
     * # CLI flag: -memberlist.abort-if-join-fails
     * [abort_if_cluster_join_fails: <boolean> | default = false]
     *
     * # If not 0, how often to rejoin the cluster. Occasional rejoin can help to fix
     * # the cluster split issue, and is harmless otherwise. For example when using
     * # only few components as a seed nodes (via -memberlist.join), then it's
     * # recommended to use rejoin. If -memberlist.join points to dynamic service that
     * # resolves to all gossiping nodes (eg. Kubernetes headless service), then rejoin
     * # is not needed.
     * # CLI flag: -memberlist.rejoin-interval
     * [rejoin_interval: <duration> | default = 0s]
     *
     * # How long to keep LEFT ingesters in the ring.
     * # CLI flag: -memberlist.left-ingesters-timeout
     * [left_ingesters_timeout: <duration> | default = 5m]
     *
     * # How long to keep obsolete entries in the KV store.
     * # CLI flag: -memberlist.obsolete-entries-timeout
     * [obsolete_entries_timeout: <duration> | default = 30s]
     *
     * # Timeout for leaving memberlist cluster.
     * # CLI flag: -memberlist.leave-timeout
     * [leave_timeout: <duration> | default = 20s]
     *
     * # Timeout for broadcasting all remaining locally-generated updates to other
     * # nodes when shutting down. Only used if there are nodes left in the memberlist
     * # cluster, and only applies to locally-generated updates, not to broadcast
     * # messages that are result of incoming gossip updates. 0 = no timeout, wait
     * # until all locally-generated updates are sent.
     * # CLI flag: -memberlist.broadcast-timeout-for-local-updates-on-shutdown
     * [broadcast_timeout_for_local_updates_on_shutdown: <duration> | default = 10s]
     *
     * # How much space to use for keeping received and sent messages in memory for
     * # troubleshooting (two buffers). 0 to disable.
     * # CLI flag: -memberlist.message-history-buffer-bytes
     * [message_history_buffer_bytes: <int> | default = 0]
     *
     * # IP address to listen on for gossip messages. Multiple addresses may be
     * # specified. Defaults to 0.0.0.0
     * # CLI flag: -memberlist.bind-addr
     * [bind_addr: <list of strings> | default = []]
     *
     * # Port to listen on for gossip messages.
     * # CLI flag: -memberlist.bind-port
     * [bind_port: <int> | default = 7946]
     *
     * # Timeout used when connecting to other nodes to send packet.
     * # CLI flag: -memberlist.packet-dial-timeout
     * [packet_dial_timeout: <duration> | default = 2s]
     *
     * # Timeout for writing 'packet' data.
     * # CLI flag: -memberlist.packet-write-timeout
     * [packet_write_timeout: <duration> | default = 5s]
     *
     * # Maximum number of concurrent writes to other nodes.
     * # CLI flag: -memberlist.max-concurrent-writes
     * [max_concurrent_writes: <int> | default = 3]
     *
     * # Timeout for acquiring one of the concurrent write slots. After this time, the
     * # message will be dropped.
     * # CLI flag: -memberlist.acquire-writer-timeout
     * [acquire_writer_timeout: <duration> | default = 250ms]
     *
     * # Enable TLS on the memberlist transport layer.
     * # CLI flag: -memberlist.tls-enabled
     * [tls_enabled: <boolean> | default = false]
     *
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is: memberlist
     * [<tls_config>]
     */
) {
}