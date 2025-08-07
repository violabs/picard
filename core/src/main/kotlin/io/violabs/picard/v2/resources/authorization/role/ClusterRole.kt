package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/cluster-role-v1/
 *
 * ClusterRole is a cluster level, logical grouping of PolicyRules that can be
 * referenced as a unit by a RoleBinding or ClusterRoleBinding.
 *
 * apiVersion: rbac.authorization.k8s.io/v1
 *
 * import "k8s.io/api/rbac/v1"
 */
@GeneratedDsl(withListGroup = true)
data class ClusterRole(
    @DefaultValue(
        "KAPIVersion.RbacAuthorizationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.RbacAuthorizationV1,
    /**
     * Standard object's metadata.
     */
    override val metadata: ObjectMeta? = null,
    /**
     * AggregationRule is an optional field that describes how to build the
     * Rules for this ClusterRole. If AggregationRule is set, then the Rules
     * are controller managed and direct changes to Rules will be stomped by the controller.
     */
    val aggregationRule: AggregationRule? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * Rules holds all the PolicyRules for this ClusterRole
     */
    val rules: List<PolicyRule>? = null
) : AuthorizationResource<ClusterRole.Version, ObjectMeta> {
    interface Version : APIVersion
}

