package io.violabs.picard.domain

/**
 * These are basically just label to track useage
 */
interface BaseK8s

interface BaseAffinity : BaseK8s

interface BaseAffinityTerm : BaseK8s

interface BaseCondition : BaseK8s

interface BaseEnvSource : BaseK8s

interface BaseIP : BaseK8s

interface BaseKeySelector : BaseK8s

interface BaseLoadBalancerIngress : BaseK8s

interface BaseNodeAffinity : BaseK8s

interface BaseNodeSelector : BaseK8s

interface BasePort : BaseK8s

interface BaseResourceClaim : BaseK8s

interface BaseResourceMetricSource : BaseK8s

interface BaseResourceMetricStatus : BaseK8s

interface BaseSecurityContext : BaseK8s

interface BaseServiceReference : BaseK8s

interface BaseSpec : BaseK8s

interface BaseStatus : BaseK8s

interface BaseSubject : BaseK8s

interface BaseWebhook : BaseK8s