package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Subject contains a reference to the object or user identities a role
 * binding applies to. This can either hold a direct API object reference,
 * or a value for non-objects such as user and group names.
 */
@GeneratedDsl(withListGroup = true)
data class Subject(
    /**
     * Kind of object being referenced. Values defined by this API group are "User",
     * "Group", and "ServiceAccount". If the Authorizer does not recognized the kind
     * value, the Authorizer should report an error.
     */
    val kind: Kind,
    /**
     * Name of the object being referenced.
     */
    val name: String,
    /**
     * APIGroup holds the API group of the referenced subject.
     * Defaults to "" for ServiceAccount subjects.
     * Defaults to "rbac.authorization.k8s.io" for User and Group subjects.
     */
    val apiGroup: String? = null,
    /**
     * Namespace of the referenced object. If the object kind is non-namespace,
     * such as "User" or "Group", and this value is not empty the Authorizer should report an error.
     */
    val namespace: String? = null
) {
    enum class Kind {
        Group,
        ServiceAccount,
        User
    }
}

