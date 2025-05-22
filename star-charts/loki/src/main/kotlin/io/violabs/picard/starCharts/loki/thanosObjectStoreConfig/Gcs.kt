package io.violabs.picard.starCharts.loki.thanosObjectStoreConfig

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Gcs(
    /**
     *   # GCS bucket name
     *   # CLI flag: -<prefix>.gcs.bucket-name
     *   [bucket_name: <string> | default = ""]
     */
    val bucketName: String? = null,
    /**
     *   # JSON either from a Google Developers Console client_credentials.json file,
     *   # or a Google Developers service account key. Needs to be valid JSON, not a
     *   # filesystem path. If empty, fallback to Google default logic:
     *   # 1. A JSON file whose path is specified by the GOOGLE_APPLICATION_CREDENTIALS
     *   # environment variable. For workload identity federation, refer to
     *   # https://cloud.google.com/iam/docs/how-to#using-workload-identity-federation
     *   # on how to generate the JSON configuration file for on-prem/non-Google cloud
     *   # platforms.
     *   # 2. A JSON file in a location known to the gcloud command-line tool:
     *   # $HOME/.config/gcloud/application_default_credentials.json.
     *   # 3. On Google Compute Engine it fetches credentials from the metadata server.
     *   # CLI flag: -<prefix>.gcs.service-account
     *   [service_account: <string> | default = ""]
     */
    val serviceAccount: String? = null,
    /**
     *   # The maximum size of the buffer that GCS client for a single PUT request. 0
     *   # to disable buffering.
     *   # CLI flag: -<prefix>.gcs.chunk-buffer-size
     *   [chunk_buffer_size: <int> | default = 0]
     */
    val chunkBufferSize: Int? = null,
    /**
     *   # The maximum number of retries for idempotent operations. Overrides the
     *   # default gcs storage client behavior if this value is greater than 0. Set
     *   # this to 1 to disable retries.
     *   # CLI flag: -<prefix>.gcs.max-retries
     *   [max_retries: <int> | default = 10]
     */
    val maxRetries: Int? = null
)