package io.violabs.picard.domain.k8sResources.authentication.serviceAccount

import io.violabs.picard.domain.LocalObjectReference
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class ServiceAccount(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val automountServiceAccountToken: Boolean? = null,
    val imagePullSecrets: List<LocalObjectReference>? = null,
    val secrets: List<ObjectReference>? = null
) : K8sResource<ServiceAccount.Version> {

    interface Version : APIVersion
}