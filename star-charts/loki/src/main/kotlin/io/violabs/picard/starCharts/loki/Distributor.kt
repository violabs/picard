package io.violabs.picard.starCharts.loki

class Distributor(
    /**
     * ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -distributor.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -distributor.ring.prefix
     *     [prefix: <string> | default = "collectors/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: distributor.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: distributor.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -distributor.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -distributor.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -distributor.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -distributor.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Period at which to heartbeat to the ring. 0 = disabled.
     *   # CLI flag: -distributor.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 5s]
     *
     *   # The heartbeat timeout after which distributors are considered unhealthy
     *   # within the ring. 0 = never (timeout disabled).
     *   # CLI flag: -distributor.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # Name of network interface to read address from.
     *   # CLI flag: -distributor.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     * # Number of workers to push batches to ingesters.
     * # CLI flag: -distributor.push-worker-count
     * [push_worker_count: <int> | default = 256]
     *
     * rate_store:
     *   # The max number of concurrent requests to make to ingester stream apis
     *   # CLI flag: -distributor.rate-store.max-request-parallelism
     *   [max_request_parallelism: <int> | default = 200]
     *
     *   # The interval on which distributors will update current stream rates from
     *   # ingesters
     *   # CLI flag: -distributor.rate-store.stream-rate-update-interval
     *   [stream_rate_update_interval: <duration> | default = 1s]
     *
     *   # Timeout for communication between distributors and any given ingester when
     *   # updating rates
     *   # CLI flag: -distributor.rate-store.ingester-request-timeout
     *   [ingester_request_timeout: <duration> | default = 500ms]
     *
     *   # If enabled, detailed logs and spans will be emitted.
     *   # CLI flag: -distributor.rate-store.debug
     *   [debug: <boolean> | default = false]
     *
     * # Customize the logging of write failures.
     * write_failures_logging:
     *   # Log volume allowed (per second). Default: 1KB.
     *   # CLI flag: -distributor.write-failures-logging.rate
     *   [rate: <int> | default = 1KB]
     *
     *   # Whether a insight=true key should be logged or not. Default: false.
     *   # CLI flag: -distributor.write-failures-logging.add-insights-label
     *   [add_insights_label: <boolean> | default = false]
     *
     * otlp_config:
     *   # List of default otlp resource attributes to be picked as index labels
     *   # CLI flag: -distributor.otlp.default_resource_attributes_as_index_labels
     *   [default_resource_attributes_as_index_labels: <list of strings> | default = [service.name service.namespace service.instance.id deployment.environment deployment.environment.name cloud.region cloud.availability_zone k8s.cluster.name k8s.namespace.name k8s.pod.name k8s.container.name container.name k8s.replicaset.name k8s.deployment.name k8s.statefulset.name k8s.daemonset.name k8s.cronjob.name k8s.job.name]]
     *
     * # Enable writes to Kafka during Push requests.
     * # CLI flag: -distributor.kafka-writes-enabled
     * [kafka_writes_enabled: <boolean> | default = false]
     *
     * # Enable writes to Ingesters during Push requests. Defaults to true.
     * # CLI flag: -distributor.ingester-writes-enabled
     * [ingester_writes_enabled: <boolean> | default = true]
     *
     * # Enable checking limits against the ingest-limits service. Defaults to false.
     * # CLI flag: -distributor.ingest-limits-enabled
     * [ingest_limits_enabled: <boolean> | default = false]
     *
     * # Enable dry-run mode where limits are checked the ingest-limits service, but
     * # not enforced. Defaults to false.
     * # CLI flag: -distributor.ingest-limits-dry-run-enabled
     * [ingest_limits_dry_run_enabled: <boolean> | default = false]
     *
     * tenant_topic:
     *   # Enable the tenant topic tee, which writes logs to Kafka topics based on
     *   # tenant IDs instead of using multitenant topics/partitions.
     *   # CLI flag: -distributor.tenant-topic-tee.enabled
     *   [enabled: <boolean> | default = false]
     *
     *   # Prefix to prepend to tenant IDs to form the final Kafka topic name
     *   # CLI flag: -distributor.tenant-topic-tee.topic-prefix
     *   [topic_prefix: <string> | default = "loki.tenant"]
     *
     *   # Maximum number of bytes that can be buffered before producing to Kafka
     *   # CLI flag: -distributor.tenant-topic-tee.max-buffered-bytes
     *   [max_buffered_bytes: <int> | default = 100MiB]
     *
     *   # Maximum size of a single Kafka record in bytes
     *   # CLI flag: -distributor.tenant-topic-tee.max-record-size-bytes
     *   [max_record_size_bytes: <int> | default = 15MiB249KiB]
     *
     *   # Topic strategy to use. Valid values are 'simple' or 'automatic'
     *   # CLI flag: -distributor.tenant-topic-tee.strategy
     *   [strategy: <string> | default = "simple"]
     */
) {
}