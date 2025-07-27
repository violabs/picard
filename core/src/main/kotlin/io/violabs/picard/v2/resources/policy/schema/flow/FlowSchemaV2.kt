package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/policy-resources/flow-schema-v1/
 *
 * FlowSchema defines the schema of a group of flows. Note that a
 * flow is made up of a set of inbound API requests with similar
 * attributes and is identified by a pair of strings: the name of
 * the FlowSchema and a "flow distinguisher".
 *
 * apiVersion: flowcontrol.apiserver.k8s.io/v1
 *
 * import "k8s.io/api/flowcontrol/v1"
 */
@GeneratedDsl
data class FlowSchemaV2(
    @DefaultValue(
        "KAPIVersion.FlowControlApiServerV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val metadata: ObjectMeta? = null,
    /**
     * is the specification of the desired behavior of a FlowSchema.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: FlowSchemaSpec? = null,
    /**
     * status is the current status of a FlowSchema.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: FlowSchemaStatus? = null
) : PolicyResource<FlowSchemaV2.Version, ObjectMeta> {
    interface Version : APIVersion
}
