package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.HTTPVerb

data class NonResourceRule(
    val verbs: List<HTTPVerb>,
    val nonResourceURLs: String? = null
)
