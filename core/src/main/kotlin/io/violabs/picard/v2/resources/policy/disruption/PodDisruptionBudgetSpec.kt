package io.violabs.picard.v2.resources.policy.disruption

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.v2.common.LabelSelector

/**
 * PodDisruptionBudgetSpec is a description of a PodDisruptionBudget.
 */
@GeneratedDsl
data class PodDisruptionBudgetSpec(
    /**
     * An eviction is allowed if at most "maxUnavailable" pods selected by "selector" are unavailable after the eviction,
     * i.e. even in absence of the evicted pod. For example, one can prevent all voluntary evictions by specifying 0.
     * This is a mutually exclusive setting with "minAvailable".
     */
    val maxUnavailable: IntOrString? = null,
    /**
     * An eviction is allowed if at least "minAvailable" pods selected by "selector" will still be available after the eviction,
     * i.e. even in the absence of the evicted pod. So for example you can prevent all voluntary evictions by specifying "100%".
     */
    val minAvailable: IntOrString? = null,
    /**
     * Label query over pods whose evictions are managed by the disruption budget.
     * A null selector will match no pods, while an empty ({}) selector will select all pods within the namespace.
     */
    val selector: LabelSelector? = null,
    /**
     * UnhealthyPodEvictionPolicy defines the criteria for when unhealthy pods should be considered for eviction.
     * Current implementation considers healthy pods, as pods that have status.conditions item with type="Ready",status="True".
     * 
     * Valid policies are IfHealthyBudget and AlwaysAllow. If no policy is specified, the default behavior will be used,
     * which corresponds to the IfHealthyBudget policy.
     */
    val unhealthyPodEvictionPolicy: String? = null
)