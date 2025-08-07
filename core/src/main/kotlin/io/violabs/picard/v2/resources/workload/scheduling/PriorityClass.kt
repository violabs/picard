package io.violabs.picard.v2.resources.workload.scheduling

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/workload-resources/priority-class-v1/
 *
 * PriorityClass defines mapping from a priority class name to the priority integer value. 
 * The value can be any valid integer.
 *
 * apiVersion: scheduling.k8s.io/v1
 *
 * import "k8s.io/api/scheduling/v1"
 */
@GeneratedDsl(withListGroup = true)
data class PriorityClass(
    @DefaultValue(
        "KAPIVersion.SchedulingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    /**
     * Standard object's metadata. 
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * value represents the integer value of this priority class. This is the actual priority 
     * that pods receive when they have the name of this class in their pod spec.
     */
    val value: Int,
    /**
     * description is an arbitrary string that usually provides guidelines on when this priority class should be used.
     */
    val description: String? = null,
    /**
     * globalDefault specifies whether this PriorityClass should be considered as the default priority 
     * for pods that do not have any priority class. Only one PriorityClass can be marked as globalDefault. 
     * However, if more than one PriorityClasses exists with their globalDefault field set to true, 
     * the smallest value of such global default PriorityClasses will be used as the default priority.
     */
    val globalDefault: Boolean? = null,
    /**
     * preemptionPolicy is the Policy for preempting pods with lower priority. 
     * One of Never, PreemptLowerPriority. Defaults to PreemptLowerPriority if unset.
     */
    val preemptionPolicy: PreemptionPolicy? = null
) : WorkloadResource<PriorityClass.Version, ObjectMeta> {
    interface Version : APIVersion

    enum class PreemptionPolicy {
        Never,
        PreemptLowerPriority;
    }
}