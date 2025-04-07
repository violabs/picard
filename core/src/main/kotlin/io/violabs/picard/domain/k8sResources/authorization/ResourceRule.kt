package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.HTTPVerb

data class ResourceRule(
    val verbs: List<HTTPVerb>,
    val apiGroups: List<String>? = null,
    val resourceNames: String? = null,
    val resources: String? = null
)
