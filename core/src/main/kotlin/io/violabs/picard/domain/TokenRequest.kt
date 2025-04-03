package io.violabs.picard.domain

data class TokenRequest(val audience: String, val expirationSeconds: Long)