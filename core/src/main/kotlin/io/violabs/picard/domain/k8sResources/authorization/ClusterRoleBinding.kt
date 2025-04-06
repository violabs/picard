package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class ClusterRoleBinding(
    override val apiVersion: Version = KAPIVersion.RBACAuthorizationV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<ClusterRoleBinding.Version> {

    interface Version : APIVersion
}