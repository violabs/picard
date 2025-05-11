package io.violabs.picard.starCharts.loki

class Ruler(
    /**
     * # Base URL of the Grafana instance.
     * # CLI flag: -ruler.external.url
     * [external_url: <url>]
     *
     * # Datasource UID for the dashboard.
     * # CLI flag: -ruler.datasource-uid
     * [datasource_uid: <string> | default = ""]
     *
     * # Labels to add to all alerts.
     * [external_labels: <list of Labels>]
     *
     * # The grpc_client block configures the gRPC client used to communicate between a
     * # client and server component in Loki.
     * # The CLI flags prefix for this block configuration is: ruler.client
     * [ruler_client: <grpc_client>]
     *
     * # How frequently to evaluate rules.
     * # CLI flag: -ruler.evaluation-interval
     * [evaluation_interval: <duration> | default = 1m]
     *
     * # How frequently to poll for rule changes.
     * # CLI flag: -ruler.poll-interval
     * [poll_interval: <duration> | default = 1m]
     *
     * # Deprecated: Use -ruler-storage. CLI flags and their respective YAML config
     * # options instead.
     * storage:
     *   # Method to use for backend rule storage (configdb, azure, gcs, s3, swift,
     *   # local, bos, cos)
     *   # CLI flag: -ruler.storage.type
     *   [type: <string> | default = ""]
     *
     *   # Configures backend rule storage for Azure.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [azure: <azure_storage_config>]
     *
     *   # Configures backend rule storage for AlibabaCloud Object Storage (OSS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [alibabacloud: <alibabacloud_storage_config>]
     *
     *   # Configures backend rule storage for GCS.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [gcs: <gcs_storage_config>]
     *
     *   # Configures backend rule storage for S3.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [s3: <s3_storage_config>]
     *
     *   # Configures backend rule storage for Baidu Object Storage (BOS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [bos: <bos_storage_config>]
     *
     *   # Configures backend rule storage for Swift.
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [swift: <swift_storage_config>]
     *
     *   # Configures backend rule storage for IBM Cloud Object Storage (COS).
     *   # The CLI flags prefix for this block configuration is: ruler.storage
     *   [cos: <cos_storage_config>]
     *
     *   # Configures backend rule storage for a local file system directory.
     *   local:
     *     # Directory to scan for rules
     *     # CLI flag: -ruler.storage.local.directory
     *     [directory: <string> | default = ""]
     *
     * # File path to store temporary rule files.
     * # CLI flag: -ruler.rule-path
     * [rule_path: <string> | default = "/rules"]
     *
     * # Comma-separated list of Alertmanager URLs to send notifications to. Each
     * # Alertmanager URL is treated as a separate group in the configuration. Multiple
     * # Alertmanagers in HA per group can be supported by using DNS resolution via
     * # '-ruler.alertmanager-discovery'.
     * # CLI flag: -ruler.alertmanager-url
     * [alertmanager_url: <string> | default = ""]
     *
     * # Use DNS SRV records to discover Alertmanager hosts.
     * # CLI flag: -ruler.alertmanager-discovery
     * [enable_alertmanager_discovery: <boolean> | default = false]
     *
     * # How long to wait between refreshing DNS resolutions of Alertmanager hosts.
     * # CLI flag: -ruler.alertmanager-refresh-interval
     * [alertmanager_refresh_interval: <duration> | default = 1m]
     *
     * # Use Alertmanager APIv2. APIv1 was deprecated in Alertmanager 0.16.0 and is
     * # removed as of 0.27.0.
     * # CLI flag: -ruler.alertmanager-use-v2
     * [enable_alertmanager_v2: <boolean> | default = true]
     *
     * # List of alert relabel configs.
     * [alert_relabel_configs: <relabel_config...>]
     *
     * # Capacity of the queue for notifications to be sent to the Alertmanager.
     * # CLI flag: -ruler.notification-queue-capacity
     * [notification_queue_capacity: <int> | default = 10000]
     *
     * # HTTP timeout duration when sending notifications to the Alertmanager.
     * # CLI flag: -ruler.notification-timeout
     * [notification_timeout: <duration> | default = 10s]
     *
     * alertmanager_client:
     *   # The TLS configuration.
     *   # The CLI flags prefix for this block configuration is:
     *   # ruler.alertmanager-client
     *   [<tls_config>]
     *
     *   # HTTP Basic authentication username. It overrides the username set in the URL
     *   # (if any).
     *   # CLI flag: -ruler.alertmanager-client.basic-auth-username
     *   [basic_auth_username: <string> | default = ""]
     *
     *   # HTTP Basic authentication password. It overrides the password set in the URL
     *   # (if any).
     *   # CLI flag: -ruler.alertmanager-client.basic-auth-password
     *   [basic_auth_password: <string> | default = ""]
     *
     *   # HTTP Header authorization type (default: Bearer).
     *   # CLI flag: -ruler.alertmanager-client.type
     *   [type: <string> | default = "Bearer"]
     *
     *   # HTTP Header authorization credentials.
     *   # CLI flag: -ruler.alertmanager-client.credentials
     *   [credentials: <string> | default = ""]
     *
     *   # HTTP Header authorization credentials file.
     *   # CLI flag: -ruler.alertmanager-client.credentials-file
     *   [credentials_file: <string> | default = ""]
     *
     * # Max time to tolerate outage for restoring "for" state of alert.
     * # CLI flag: -ruler.for-outage-tolerance
     * [for_outage_tolerance: <duration> | default = 1h]
     *
     * # Minimum duration between alert and restored "for" state. This is maintained
     * # only for alerts with configured "for" time greater than the grace period.
     * # CLI flag: -ruler.for-grace-period
     * [for_grace_period: <duration> | default = 10m]
     *
     * # Minimum amount of time to wait before resending an alert to Alertmanager.
     * # CLI flag: -ruler.resend-delay
     * [resend_delay: <duration> | default = 1m]
     *
     * # Distribute rule evaluation using ring backend.
     * # CLI flag: -ruler.enable-sharding
     * [enable_sharding: <boolean> | default = false]
     *
     * # The sharding strategy to use. Supported values are: default, shuffle-sharding.
     * # CLI flag: -ruler.sharding-strategy
     * [sharding_strategy: <string> | default = "default"]
     *
     * # The sharding algorithm to use for deciding how rules & groups are sharded.
     * # Supported values are: by-group, by-rule.
     * # CLI flag: -ruler.sharding-algo
     * [sharding_algo: <string> | default = "by-group"]
     *
     * # Time to spend searching for a pending ruler when shutting down.
     * # CLI flag: -ruler.search-pending-for
     * [search_pending_for: <duration> | default = 5m]
     *
     * # Ring used by Loki ruler. The CLI flags prefix for this block configuration is
     * # 'ruler.ring'.
     * ring:
     *   kvstore:
     *     # Backend storage to use for the ring. Supported values are: consul, etcd,
     *     # inmemory, memberlist, multi.
     *     # CLI flag: -ruler.ring.store
     *     [store: <string> | default = "consul"]
     *
     *     # The prefix for the keys in the store. Should end with a /.
     *     # CLI flag: -ruler.ring.prefix
     *     [prefix: <string> | default = "rulers/"]
     *
     *     # Configuration for a Consul client. Only applies if the selected kvstore is
     *     # consul.
     *     # The CLI flags prefix for this block configuration is: ruler.ring
     *     [consul: <consul>]
     *
     *     # Configuration for an ETCD v3 client. Only applies if the selected kvstore
     *     # is etcd.
     *     # The CLI flags prefix for this block configuration is: ruler.ring
     *     [etcd: <etcd>]
     *
     *     multi:
     *       # Primary backend storage used by multi-client.
     *       # CLI flag: -ruler.ring.multi.primary
     *       [primary: <string> | default = ""]
     *
     *       # Secondary backend storage used by multi-client.
     *       # CLI flag: -ruler.ring.multi.secondary
     *       [secondary: <string> | default = ""]
     *
     *       # Mirror writes to secondary store.
     *       # CLI flag: -ruler.ring.multi.mirror-enabled
     *       [mirror_enabled: <boolean> | default = false]
     *
     *       # Timeout for storing value to secondary store.
     *       # CLI flag: -ruler.ring.multi.mirror-timeout
     *       [mirror_timeout: <duration> | default = 2s]
     *
     *   # Interval between heartbeats sent to the ring. 0 = disabled.
     *   # CLI flag: -ruler.ring.heartbeat-period
     *   [heartbeat_period: <duration> | default = 5s]
     *
     *   # The heartbeat timeout after which ruler ring members are considered
     *   # unhealthy within the ring. 0 = never (timeout disabled).
     *   # CLI flag: -ruler.ring.heartbeat-timeout
     *   [heartbeat_timeout: <duration> | default = 1m]
     *
     *   # Name of network interface to read addresses from.
     *   # CLI flag: -ruler.ring.instance-interface-names
     *   [instance_interface_names: <list of strings> | default = [<private network interfaces>]]
     *
     *   # The number of tokens the lifecycler will generate and put into the ring if
     *   # it joined without transferring tokens from another lifecycler.
     *   # CLI flag: -ruler.ring.num-tokens
     *   [num_tokens: <int> | default = 128]
     *
     * # Period with which to attempt to flush rule groups.
     * # CLI flag: -ruler.flush-period
     * [flush_period: <duration> | default = 1m]
     *
     * # Enable the ruler API.
     * # CLI flag: -ruler.enable-api
     * [enable_api: <boolean> | default = true]
     *
     * # Comma separated list of tenants whose rules this ruler can evaluate. If
     * # specified, only these tenants will be handled by ruler, otherwise this ruler
     * # can process rules from all tenants. Subject to sharding.
     * # CLI flag: -ruler.enabled-tenants
     * [enabled_tenants: <string> | default = ""]
     *
     * # Comma separated list of tenants whose rules this ruler cannot evaluate. If
     * # specified, a ruler that would normally pick the specified tenant(s) for
     * # processing will ignore them instead. Subject to sharding.
     * # CLI flag: -ruler.disabled-tenants
     * [disabled_tenants: <string> | default = ""]
     *
     * # Report the wall time for ruler queries to complete as a per user metric and as
     * # an info level log message.
     * # CLI flag: -ruler.query-stats-enabled
     * [query_stats_enabled: <boolean> | default = false]
     *
     * # Disable the rule_group label on exported metrics.
     * # CLI flag: -ruler.disable-rule-group-label
     * [disable_rule_group_label: <boolean> | default = false]
     *
     * wal:
     *   # The directory in which to write tenant WAL files. Each tenant will have its
     *   # own directory one level below this directory.
     *   # CLI flag: -ruler.wal.dir
     *   [dir: <string> | default = "ruler-wal"]
     *
     *   # Frequency with which to run the WAL truncation process.
     *   # CLI flag: -ruler.wal.truncate-frequency
     *   [truncate_frequency: <duration> | default = 1h]
     *
     *   # Minimum age that samples must exist in the WAL before being truncated.
     *   # CLI flag: -ruler.wal.min-age
     *   [min_age: <duration> | default = 5m]
     *
     *   # Maximum age that samples must exist in the WAL before being truncated.
     *   # CLI flag: -ruler.wal.max-age
     *   [max_age: <duration> | default = 4h]
     *
     * wal_cleaner:
     *   # The minimum age of a WAL to consider for cleaning.
     *   # CLI flag: -ruler.wal-cleaner.min-age
     *   [min_age: <duration> | default = 12h]
     *
     *   # How often to run the WAL cleaner. 0 = disabled.
     *   # CLI flag: -ruler.wal-cleaner.period
     *   [period: <duration> | default = 0s]
     *
     * # Remote-write configuration to send rule samples to a Prometheus remote-write
     * # endpoint.
     * remote_write:
     *   # Deprecated: Use 'clients' instead. Configure remote write client.
     *   [client: <RemoteWriteConfig>]
     *
     *   # Configure remote write clients. A map with remote client id as key. For
     *   # details, see
     *   # https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write
     *   # Specifying a header with key 'X-Scope-OrgID' under the 'headers' section of
     *   # RemoteWriteConfig is not permitted. If specified, it will be dropped during
     *   # config parsing.
     *   [clients: <map of string to RemoteWriteConfig>]
     *
     *   # Enable remote-write functionality.
     *   # CLI flag: -ruler.remote-write.enabled
     *   [enabled: <boolean> | default = false]
     *
     *   # Minimum period to wait between refreshing remote-write reconfigurations.
     *   # This should be greater than or equivalent to
     *   # -limits.per-user-override-period.
     *   # CLI flag: -ruler.remote-write.config-refresh-period
     *   [config_refresh_period: <duration> | default = 10s]
     *
     *   # Add an X-Scope-OrgID header in remote write requests with the tenant ID of a
     *   # Loki tenant that the recording rules are part of.
     *   # CLI flag: -ruler.remote-write.add-org-id-header
     *   [add_org_id_header: <boolean> | default = true]
     *
     * # Configuration for rule evaluation.
     * evaluation:
     *   # The evaluation mode for the ruler. Can be either 'local' or 'remote'. If set
     *   # to 'local', the ruler will evaluate rules locally. If set to 'remote', the
     *   # ruler will evaluate rules remotely. If unset, the ruler will evaluate rules
     *   # locally.
     *   # CLI flag: -ruler.evaluation.mode
     *   [mode: <string> | default = "local"]
     *
     *   # Upper bound of random duration to wait before rule evaluation to avoid
     *   # contention during concurrent execution of rules. Jitter is calculated
     *   # consistently for a given rule. Set 0 to disable (default).
     *   # CLI flag: -ruler.evaluation.max-jitter
     *   [max_jitter: <duration> | default = 0s]
     *
     *   query_frontend:
     *     # GRPC listen address of the query-frontend(s). Must be a DNS address
     *     # (prefixed with dns:///) to enable client side load balancing.
     *     # CLI flag: -ruler.evaluation.query-frontend.address
     *     [address: <string> | default = ""]
     *
     *     # Set to true if query-frontend connection requires TLS.
     *     # CLI flag: -ruler.evaluation.query-frontend.tls-enabled
     *     [tls_enabled: <boolean> | default = false]
     *
     *     # The TLS configuration.
     *     # The CLI flags prefix for this block configuration is:
     *     # ruler.evaluation.query-frontend
     *     [<tls_config>]
     */
) {
}