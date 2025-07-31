package io.violabs.picard.v2.resources.service.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class SessionAffinityConfig(
    /**
     * clientIP contains the configurations of Client IP based session affinity.
     */
    val clientIP: ClientIPConfig? = null
)