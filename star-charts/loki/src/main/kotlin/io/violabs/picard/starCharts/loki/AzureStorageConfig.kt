package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL(
    withMapGroup = "SINGLE"
)
class AzureStorageConfig(
    /**
     * # Azure Cloud environment. Supported values are: AzureGlobal, AzureChinaCloud,
     * # AzureGermanCloud, AzureUSGovernment.
     * # CLI flag: -<prefix>.azure.environment
     * [environment: <string> | default = "AzureGlobal"]
     */
    val environment: Environment? = null,
    /**
     * # Azure storage account name.
     * # CLI flag: -<prefix>.azure.account-name
     * [account_name: <string> | default = ""]
     */
    val accountName: String? = null,
    /**
     * # Azure storage account key.
     * # CLI flag: -<prefix>.azure.account-key
     * [account_key: <string> | default = ""]
     */
    val accountKey: String? = null,
    /**
     * # If `connection-string` is set, the values of `account-name` and
     * # `endpoint-suffix` values will not be used. Use this method over `account-key`
     * # if you need to authenticate via a SAS token. Or if you use the Azurite
     * # emulator.
     * # CLI flag: -<prefix>.azure.connection-string
     * [connection_string: <string> | default = ""]
     */
    val connectionString: String? = null,
    /**
     * # Name of the storage account blob container used to store chunks. This
     * # container must be created before running cortex.
     * # CLI flag: -<prefix>.azure.container-name
     * [container_name: <string> | default = "loki"]
     */
    val containerName: String? = null,
    /**
     * # Azure storage endpoint suffix without schema. The storage account name will be
     * # prefixed to this value to create the FQDN.
     * # CLI flag: -<prefix>.azure.endpoint-suffix
     * [endpoint_suffix: <string> | default = ""]
     */
    val endpointSuffix: String? = null,
    /**
     * # Use Managed Identity to authenticate to the Azure storage account.
     * # CLI flag: -<prefix>.azure.use-managed-identity
     * [use_managed_identity: <boolean> | default = false]
     */
    val useManagedIdentity: Boolean? = null,
    /**
     * # Use Federated Token to authenticate to the Azure storage account.
     * # CLI flag: -<prefix>.azure.use-federated-token
     * [use_federated_token: <boolean> | default = false]
     */
    val useFederatedToken: Boolean? = null,
    /**
     * # User assigned identity ID to authenticate to the Azure storage account.
     * # CLI flag: -<prefix>.azure.user-assigned-id
     * [user_assigned_id: <string> | default = ""]
     */
    val userAssignedId: String? = null,
    /**
     * # Use Service Principal to authenticate through Azure OAuth.
     * # CLI flag: -<prefix>.azure.use-service-principal
     * [use_service_principal: <boolean> | default = false]
     */
    val useServicePrincipal: Boolean? = null,
    /**
     * # Azure Service Principal ID(GUID).
     * # CLI flag: -<prefix>.azure.client-id
     * [client_id: <string> | default = ""]
     */
    val clientId: String? = null,
    /**
     * # Azure Service Principal secret key.
     * # CLI flag: -<prefix>.azure.client-secret
     * [client_secret: <string> | default = ""]
     */
    val clientSecret: String? = null,
    /**
     * # Azure Tenant ID is used to authenticate through Azure OAuth.
     * # CLI flag: -<prefix>.azure.tenant-id
     * [tenant_id: <string> | default = ""]
     */
    val tenantId: String? = null,
    /**
     * # Chunk delimiter for blob ID to be used
     * # CLI flag: -<prefix>.azure.chunk-delimiter
     * [chunk_delimiter: <string> | default = "-"]
     */
    val chunkDelimiter: String? = null,
    /**
     * # Preallocated buffer size for downloads.
     * # CLI flag: -<prefix>.azure.download-buffer-size
     * [download_buffer_size: <int> | default = 512000]
     */
    val downloadBufferSize: Int? = null,
    /**
     * # Preallocated buffer size for uploads.
     * # CLI flag: -<prefix>.azure.upload-buffer-size
     * [upload_buffer_size: <int> | default = 256000]
     */
    val uploadBufferSize: Int? = null,
    /**
     * # Number of buffers used to used to upload a chunk.
     * # CLI flag: -<prefix>.azure.download-buffer-count
     * [upload_buffer_count: <int> | default = 1]
     */
    val uploadBufferCount: Int? = null,
    /**
     * # Timeout for requests made against azure blob storage.
     * # CLI flag: -<prefix>.azure.request-timeout
     * [request_timeout: <duration> | default = 30s]
     */
    val requestTimeout: String? = null,
    /**
     * # Number of retries for a request which times out.
     * # CLI flag: -<prefix>.azure.max-retries
     * [max_retries: <int> | default = 5]
     */
    val maxRetries: Int? = null,
    /**
     * # Minimum time to wait before retrying a request.
     * # CLI flag: -<prefix>.azure.min-retry-delay
     * [min_retry_delay: <duration> | default = 10ms]
     */
    val minRetryDelay: Duration? = null,
    /**
     * # Maximum time to wait before retrying a request.
     * # CLI flag: -<prefix>.azure.max-retry-delay
     * [max_retry_delay: <duration> | default = 500ms]
     */
    val maxRetryDelay: Duration? = null,
) {

    enum class Environment {
        AzureGlobal,
        AzureChinaCloud,
        AzureGermanCloud,
        AzureUSGovernment
    }
}