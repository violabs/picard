package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

/**
 * CertificateSigningRequestCondition describes a condition of a CertificateSigningRequest object
 */
@GeneratedDsl(withListGroup = true)
data class CertificateSigningRequestCondition(
    /**
     * status of the condition, one of True, False, Unknown. Approved, Denied, and
     * Failed conditions may not be "False" or "Unknown".
     */
    val status: Status,
    /**
     * type of the condition. Known conditions are "Approved", "Denied", and "Failed".
     * An "Approved" condition is added via the /approval subresource, indicating the
     * request was approved and should be issued by the signer.
     *
     * A "Denied" condition is added via the /approval subresource, indicating the request
     * was denied and should not be issued by the signer.
     *
     * A "Failed" condition is added via the /status subresource, indicating the signer
     * failed to issue the certificate.
     *
     * Approved and Denied conditions are mutually exclusive. Approved, Denied, and
     * Failed conditions cannot be removed once added.
     *
     * Only one condition of a given type is allowed.
     */
    val type: Type,
    /**
     * lastTransitionTime is the time the condition last transitioned from one status to
     * another. If unset, when a new condition type is added or an existing condition's
     * status is changed, the server defaults this to the current time.
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val lastTransitionTime: LocalDateTime? = null,
    /**
     * lastUpdateTime is the time of the last update to this condition
     *
     * Time is a wrapper around time.Time which supports correct marshaling to YAML and JSON.
     * Wrappers are provided for many of the factory methods that the time package offers.
     */
    val lastUpdateTime: LocalDateTime? = null,
    /**
     * message contains a human readable message with details about the request state
     */
    val message: String? = null,
    /**
     * reason indicates a brief reason for the request state
     */
    val reason: String? = null
) {
    enum class Status {
        True, False, Unknown
    }

    enum class Type {
        Approved, Denied, Failed
    }
}

