package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

data class Azure(
    /**
     * azure:
     *   # Azure storage account name
     *   # CLI flag: -<prefix>.azure.account-name
     *   [account_name: <string> | default = ""]
     */
    val accountName: String? = null,
    /**
     *   # Azure storage account key. If unset, Azure managed identities will be used
     *   # for authentication instead.
     *   # CLI flag: -<prefix>.azure.account-key
     *   [account_key: <string> | default = ""]
     */
    val accountKey: String? = null,
    /**
     *   # If `connection-string` is set, the value of `endpoint-suffix` will not be
     *   # used. Use this method over `account-key` if you need to authenticate via a
     *   # SAS token. Or if you use the Azurite emulator.
     *   # CLI flag: -<prefix>.azure.connection-string
     *   [connection_string: <string> | default = ""]
     */
    val connectionString: String? = null,
    /**
     *   # Azure storage container name
     *   # CLI flag: -<prefix>.azure.container-name
     *   [container_name: <string> | default = ""]
     */
    val containerName: String? = null,
    /**
     *   # Azure storage endpoint suffix without schema. The account name will be
     *   # prefixed to this value to create the FQDN. If set to empty string, default
     *   # endpoint suffix is used.
     *   # CLI flag: -<prefix>.azure.endpoint-suffix
     *   [endpoint_suffix: <string> | default = ""]
     */
    val endpointSuffix: String? = null,
    /**
     *   # Number of retries for recoverable errors
     *   # CLI flag: -<prefix>.azure.max-retries
     *   [max_retries: <int> | default = 20]
     */
    val maxRetries: Int? = null,
    /**
     *   # User assigned managed identity. If empty, then System assigned identity is
     *   # used.
     *   # CLI flag: -<prefix>.azure.user-assigned-id
     *   [user_assigned_id: <string> | default = ""]
     */
    val userAssignedId: String? = null,
    /**
     *   # Delimiter used to replace ':' in chunk IDs when storing chunks
     *   # CLI flag: -<prefix>.azure.chunk-delimiter
     *   [chunk_delimiter: <string> | default = "-"]
     */
     val chunkDelimiter: String? = null
)