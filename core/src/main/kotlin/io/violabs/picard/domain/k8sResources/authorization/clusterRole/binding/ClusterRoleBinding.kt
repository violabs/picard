package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.K8sSubject
import io.violabs.picard.domain.k8sResources.authorization.RoleRef

class ClusterRoleBinding(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val roleRef: RoleRef,
    val subjects: List<K8sSubject>? = null
) : K8sResource<ClusterRoleBinding.Version> {

    interface Version : APIVersion
}