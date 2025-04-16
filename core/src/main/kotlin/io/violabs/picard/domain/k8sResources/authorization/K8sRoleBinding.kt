package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.BaseRoleBinding

interface K8sRoleBinding : BaseRoleBinding {
    val roleRef: RoleRef
}