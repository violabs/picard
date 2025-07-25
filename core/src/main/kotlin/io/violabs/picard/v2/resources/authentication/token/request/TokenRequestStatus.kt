package io.violabs.picard.v2.resources.authentication.token.request

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import java.time.LocalDateTime

@GeneratedDsl
data class TokenRequestStatus(
    /**
     * ExpirationTimestamp is the time of expiration of the returned token.
     */
    val expirationTimestamp: LocalDateTime,
    /**
     * Token is the opaque bearer token.
     */
    val token: String
)

