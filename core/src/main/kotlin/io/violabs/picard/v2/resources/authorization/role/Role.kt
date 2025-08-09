package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/role-v1/
 *
 * Role is a namespaced, logical grouping of PolicyRules that can be
 * referenced as a unit by a RoleBinding.
 *
 * apiVersion: rbac.authorization.k8s.io/v1
 *
 * import "k8s.io/api/rbac/v1"
 */
@GeneratedDsl(withListGroup = true)
data class Role(
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
     * Atomic: will be replaced during a merge
     *
     * Rules holds all the PolicyRules for this Role
     */
    val rules: List<PolicyRule>? = null
) : AuthorizationResource<Role.Version, ObjectMeta> {
    interface Version : APIVersion
}

