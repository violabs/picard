package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.ObjectMetadata

object JobTemplate {
    data class Spec(
        val metadata: ObjectMetadata? = null,
        val spec: Job.Spec? = null
    )
}