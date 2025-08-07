package io.violabs.picard.v2.resources.policy.level

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * PriorityLevelConfiguration represents the configuration of a priority level.
 * 
 * PriorityLevelConfigurationV2 represents the configuration of a priority level.
 * It defines how requests are handled for different priority levels in the flow control system.
 * 
 * apiVersion: flowcontrol.apiserver.k8s.io/v1
 * kind: PriorityLevelConfiguration
 */
@GeneratedDsl(withListGroup = true)
data class PriorityLevelConfigurationV2(
    @DefaultValue(
        "KAPIVersion.FlowControlApiServerV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the specification of the desired behavior of a "request-priority".
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: PriorityLevelConfigurationSpec? = null,
    /**
     * status is the current status of a "request-priority".
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: PriorityLevelConfigurationStatus? = null
) : PolicyResource<PriorityLevelConfigurationV2.Version, ObjectMeta> {
    interface Version : APIVersion
}