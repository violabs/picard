package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/role-binding-v1/
 *
 * RoleBinding references a role, but does not contain it. It can reference a
 * Role in the same namespace or a ClusterRole in the global namespace. It
 * adds who information via Subjects and namespace information by which namespace
 * it exists in. RoleBindings in a given namespace only have effect in that namespace.
 *
 * apiVersion: rbac.authorization.k8s.io/v1
 *
 * import "k8s.io/api/rbac/v1"
 */
@GeneratedDsl(withListGroup = true)
data class RoleBinding(
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
     * RoleRef can reference a Role in the current namespace or a ClusterRole
     * in the global namespace. If the RoleRef cannot be resolved, the
     * Authorizer must return an error. This field is immutable.
     */
    val roleRef: RoleRef,
    /**
     * Atomic: will be replaced during a merge
     *
     * Subjects holds references to the objects the role applies to.
     */
    val subjects: List<Subject>? = null
) : AuthorizationResource<RoleBinding.Version, ObjectMeta> {
    interface Version : APIVersion
}

