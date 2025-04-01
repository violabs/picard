package io.violabs.picard.domain

data class PodTemplate(
    val spec: Spec,
    val metadata: Metadata? = null
)