package io.violabs.picard.v2.resources.service.config

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ClientIPConfig(
    /**
     * timeoutSeconds specifies the seconds of ClientIP type session sticky time.
     * The value must be >0 && <=86400(for 1 day) if ServiceAffinity == "ClientIP".
     * Default value is 10800(for 3 hours).
     */
    val timeoutSeconds: Int? = null
)