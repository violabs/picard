package io.violabs.picard.starCharts.loki.ruler

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.GrpcClient
import io.violabs.picard.starCharts.loki.ring.Ring
import io.violabs.picard.starCharts.loki.tls.TlsConfig

@GeneratedDsl
class Ruler(
    /**
     * # Base URL of the Grafana instance.
     * # CLI flag: -ruler.external.url
     * [external_url: <url>]
     */
    val externalUrl: String? = null,
    /**
     * # Datasource UID for the dashboard.
     * # CLI flag: -ruler.datasource-uid
     * [datasource_uid: <string> | default = ""]
     */
    val datasourceUid: String? = null,
    /**
     * # Labels to add to all alerts.
     * [external_labels: <list of Labels>]
     */
    val externalLabels: List<Any>? = null,
    /**
     * # The grpc_client block configures the gRPC client used to communicate between a
     * # client and server component in Loki.
     * # The CLI flags prefix for this block configuration is: ruler.client
     * [ruler_client: <grpc_client>]
     */
    val rulerClient: GrpcClient? = null,
    /**
     * # How frequently to evaluate rules.
     * # CLI flag: -ruler.evaluation-interval
     * [evaluation_interval: <duration> | default = 1m]
     */
    val evaluationInterval: Duration? = null,
    /**
     * # How frequently to poll for rule changes.
     * # CLI flag: -ruler.poll-interval
     * [poll_interval: <duration> | default = 1m]
     */
    val pollInterval: Duration? = null,
    /**
     * # Deprecated: Use -ruler-storage. CLI flags and their respective YAML config
     * # options instead.
     */
    val storage: RulerStorage? = null,
    /**
     * # File path to store temporary rule files.
     * # CLI flag: -ruler.rule-path
     * [rule_path: <string> | default = "/rules"]
     */
    val rulePath: String? = null,
    /**
     * # Comma-separated list of Alertmanager URLs to send notifications to. Each
     * # Alertmanager URL is treated as a separate group in the configuration. Multiple
     * # Alertmanagers in HA per group can be supported by using DNS resolution via
     * # '-ruler.alertmanager-discovery'.
     * # CLI flag: -ruler.alertmanager-url
     * [alertmanager_url: <string> | default = ""]
     */
    @JsonProperty("alertmanagerUrl")
    val alertMangerUrl: String? = null,
    /**
     * # Use DNS SRV records to discover Alertmanager hosts.
     * # CLI flag: -ruler.alertmanager-discovery
     * [enable_alertmanager_discovery: <boolean> | default = false]
     */
    @JsonProperty("enableAlertmanagerDiscovery")
    val enableAlertManagerDiscovery: Boolean? = null,
    /**
     * # How long to wait between refreshing DNS resolutions of Alertmanager hosts.
     * # CLI flag: -ruler.alertmanager-refresh-interval
     * [alertmanager_refresh_interval: <duration> | default = 1m]
     */
    /**
     * # Use Alertmanager APIv2. APIv1 was deprecated in Alertmanager 0.16.0 and is
     * # removed as of 0.27.0.
     * # CLI flag: -ruler.alertmanager-use-v2
     * [enable_alertmanager_v2: <boolean> | default = true]
     */
    @JsonProperty("enableAlertmanagerV2")
    val enableAlertManagerV2: Boolean? = null,
    /**
     * # List of alert relabel configs.
     * [alert_relabel_configs: <relabel_config...>]
     */
    val alertRelabelConfigs: List<Any>? = null,
    /**
     * # Capacity of the queue for notifications to be sent to the Alertmanager.
     * # CLI flag: -ruler.notification-queue-capacity
     * [notification_queue_capacity: <int> | default = 10000]
     */
    val notificationQueueCapacity: Int? = null,
    /**
     * # HTTP timeout duration when sending notifications to the Alertmanager.
     * # CLI flag: -ruler.notification-timeout
     * [notification_timeout: <duration> | default = 10s]
     */
    val notificationTimeout: Duration? = null,
    /**
     * alertmanager_client:
     *   # The TLS configuration.
     *   # The CLI flags prefix for this block configuration is:
     *   # ruler.alertmanager-client
     *   [<tls_config>]
     */
    @JsonProperty("alertmanagerClient")
    val alertManagerClient: TlsConfig? = null,
    /**
     *   # HTTP Basic authentication username. It overrides the username set in the URL
     *   # (if any).
     *   # CLI flag: -ruler.alertmanager-client.basic-auth-username
     *   [basic_auth_username: <string> | default = ""]
     */
    val basicAuthUsername: String? = null,
    /**
     *   # HTTP Basic authentication password. It overrides the password set in the URL
     *   # (if any).
     *   # CLI flag: -ruler.alertmanager-client.basic-auth-password
     *   [basic_auth_password: <string> | default = ""]
     */
    val basicAuthPassword: String? = null,
    /**
     *   # HTTP Header authorization type (default: Bearer).
     *   # CLI flag: -ruler.alertmanager-client.type
     *   [type: <string> | default = "Bearer"]
     */
    val type: String? = null,
    /**
     *   # HTTP Header authorization credentials.
     *   # CLI flag: -ruler.alertmanager-client.credentials
     *   [credentials: <string> | default = ""]
     */
    val credentials: String? = null,
    /**
     *   # HTTP Header authorization credentials file.
     *   # CLI flag: -ruler.alertmanager-client.credentials-file
     *   [credentials_file: <string> | default = ""]
     */
    val credentialsFile: String? = null,
    /**
     * # Max time to tolerate outage for restoring "for" state of alert.
     * # CLI flag: -ruler.for-outage-tolerance
     * [for_outage_tolerance: <duration> | default = 1h]
     */
    val forOutageTolerance: Duration? = null,
    /**
     * # Minimum duration between alert and restored "for" state. This is maintained
     * # only for alerts with configured "for" time greater than the grace period.
     * # CLI flag: -ruler.for-grace-period
     * [for_grace_period: <duration> | default = 10m]
     */
    val forGracePeriod: Duration? = null,
    /**
     * # Minimum amount of time to wait before resending an alert to Alertmanager.
     * # CLI flag: -ruler.resend-delay
     * [resend_delay: <duration> | default = 1m]
     */
    val resendDelay: Duration? = null,
    /**
     * # Distribute rule evaluation using ring backend.
     * # CLI flag: -ruler.enable-sharding
     * [enable_sharding: <boolean> | default = false]
     */
    val enableSharding: Boolean? = null,
    /**
     * # The sharding strategy to use. Supported values are: default, shuffle-sharding.
     * # CLI flag: -ruler.sharding-strategy
     * [sharding_strategy: <string> | default = "default"]
     */
    val shardingStrategy: String? = null,
    /**
     * # The sharding algorithm to use for deciding how rules & groups are sharded.
     * # Supported values are: by-group, by-rule.
     * # CLI flag: -ruler.sharding-algo
     * [sharding_algo: <string> | default = "by-group"]
     */
    val shardingAlgo: String? = null,
    /**
     * # Time to spend searching for a pending ruler when shutting down.
     * # CLI flag: -ruler.search-pending-for
     * [search_pending_for: <duration> | default = 5m]
     */
    val searchPendingFor: Duration? = null,
    /**
     * # Ring used by Loki ruler. The CLI flags prefix for this block configuration is
     * # 'ruler.ring'.
     */
    val ring: Ring? = null,
    /**
     * # Period with which to attempt to flush rule groups.
     * # CLI flag: -ruler.flush-period
     * [flush_period: <duration> | default = 1m]
     */
    val flushPeriod: Duration? = null,
    /**
     * # Enable the ruler API.
     * # CLI flag: -ruler.enable-api
     * [enable_api: <boolean> | default = true]
     */
    val enableApi: Boolean? = null,
    /**
     * # Comma separated list of tenants whose rules this ruler can evaluate. If
     * # specified, only these tenants will be handled by ruler, otherwise this ruler
     * # can process rules from all tenants. Subject to sharding.
     * # CLI flag: -ruler.enabled-tenants
     * [enabled_tenants: <string> | default = ""]
     */
    val enabledTenants: String? = null,
    /**
     * # Comma separated list of tenants whose rules this ruler cannot evaluate. If
     * # specified, a ruler that would normally pick the specified tenant(s) for
     * # processing will ignore them instead. Subject to sharding.
     * # CLI flag: -ruler.disabled-tenants
     * [disabled_tenants: <string> | default = ""]
     */
    val disabledTenants: String? = null,
    /**
     * # Report the wall time for ruler queries to complete as a per user metric and as
     * # an info level log message.
     * # CLI flag: -ruler.query-stats-enabled
     * [query_stats_enabled: <boolean> | default = false]
     */
    val queryStatsEnabled: Boolean? = null,
    /**
     * # Disable the rule_group label on exported metrics.
     * # CLI flag: -ruler.disable-rule-group-label
     * [disable_rule_group_label: <boolean> | default = false]
     */
    val disableRuleGroupLabel: Boolean? = null,
    val wal: Wal? = null,
    val walCleaner: WalCleaner? = null,
    /**
     * # Remote-write configuration to send rule samples to a Prometheus remote-write
     * # endpoint.
     */
    val remoteWrite: RemoteWrite? = null,
    /**
     * # Configuration for rule evaluation.
     */
    val evaluation: Evaluation? = null,
    val queryFrontend: QueryFrontend? = null
)