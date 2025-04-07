package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.HTTPVerb

data class NonResourceAttributes(
    val path: String? = null,
    val verb: HTTPVerb? = null
)
