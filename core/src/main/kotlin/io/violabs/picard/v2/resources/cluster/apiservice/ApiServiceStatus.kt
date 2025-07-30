package io.violabs.picard.v2.resources.cluster.apiservice

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * APIServiceStatus contains derived information about an API server
 */
@GeneratedDsl(withListGroup = true)
data class ApiServiceStatus(
    /**
     * Current service state of apiService.
     */
    val conditions: List<ApiServiceCondition>? = null
)