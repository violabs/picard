package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class ClusterTrustBundle(
    override val apiVersion: Version = KAPIVersion.CertificatesV1Alpha1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec
) : K8sResource<ClusterTrustBundle.Version> {
    interface Version : APIVersion

    data class Spec(
        val trustBundle: String,
        val signerName: String? = null
    ) : BaseSpec
}