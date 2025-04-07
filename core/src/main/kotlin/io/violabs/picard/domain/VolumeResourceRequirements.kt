package io.violabs.picard.domain

import io.violabs.picard.domain.k8sResources.Quantity

class VolumeResourceRequirements(
    val limits: Map<String, Quantity>? = null,
    val requests: Map<String, Quantity>? = null
)