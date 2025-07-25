package io.violabs.picard.v2.resources.authentication.self

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class SelfSubjectReviewStatus(
    /**
     * User attributes of the user making this request.
     */
    val userInfo: UserInfo? = null
)
