package io.violabs.picard.v2.resources.workload.pod.template

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.workload.pod.PodSpec

@GeneratedDsl
data class PodTemplateSpec(
    val metadata: ObjectMeta? = null,
    val spec: PodSpec? = null
)