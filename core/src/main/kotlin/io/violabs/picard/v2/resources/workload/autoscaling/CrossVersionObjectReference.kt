package io.violabs.picard.v2.resources.workload.autoscaling

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CrossVersionObjectReference contains enough information to let you identify the referred resource.
 */
@GeneratedDsl
data class CrossVersionObjectReference(
    /**
     * kind is the kind of the referent;
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds
     */
    val kind: String,
    /**
     * name is the name of the referent;
     * More info: https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names
     */
    val name: String,
    /**
     * apiVersion is the API version of the referent
     */
    val apiVersion: String? = null
)