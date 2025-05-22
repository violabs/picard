package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class Sse(
    /**
     *   # Enable AWS Server Side Encryption. Supported values: SSE-KMS, SSE-S3.
     *   # CLI flag: -<prefix>.s3.sse.type
     *   [type: <string> | default = ""]
     */
    val type: Type? = null,
    /**
     *   # KMS Key ID used to encrypt objects in S3
     *   # CLI flag: -<prefix>.s3.sse.kms-key-id
     *   [kms_key_id: <string> | default = ""]
     */
    val kmsKeyId: String? = null,
    /**
     *   # KMS Encryption Context used for object encryption. It expects JSON formatted
     *   # string.
     *   # CLI flag: -<prefix>.s3.sse.kms-encryption-context
     *   [kms_encryption_context: <string> | default = ""]
     */
    val kmsEncryptionContext: String? = null,
) {
    @JsonNaming(PropertyNamingStrategies.KebabCaseStrategy::class)
    enum class Type {
        SSE_KMS, SSE_S3;
    }
}