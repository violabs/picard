package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BooleanType
import java.time.LocalDateTime

/**
 * These are basically just label to track useage
 */
interface BaseK8s

interface BaseCondition : BaseK8s {
    val status: BooleanType
    val type: String
    val lastTransitionTime: LocalDateTime?
    val message: String?
    val reason: String?
}

interface BaseEnvSource : BaseK8s {
    val name: String?
    val optional: Boolean?
}

interface BaseKeySelector : BaseK8s

interface BaseNodeAffinity : BaseK8s

interface BaseNodeSelector : BaseK8s

interface BaseResourceClaim : BaseK8s

interface BaseResourceMetricSource : BaseK8s {
    val name: String
    val target: Metric.Target?
}

interface BaseResourceMetricStatus : BaseK8s {
    val current: Metric.ValueStatus
    val name: String
}

interface BaseSecurityContext : BaseK8s

interface BaseSpec : BaseK8s

interface BaseStatus : BaseK8s