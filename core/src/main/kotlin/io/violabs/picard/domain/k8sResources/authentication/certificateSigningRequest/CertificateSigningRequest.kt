package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.SigningRequestCondition

class CertificateSigningRequest(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<CertificateSigningRequest.Version> {
    interface Version : APIVersion

    data class Spec(
        val request: List<Byte>,
        val signerName: String,
        val expirationSeconds: Int? = null,
        val extra: Map<String, List<String>>? = null,
        val groups: List<String>? = null,
        val uid: String? = null,
        val usages: List<String>? = null,
        val username: String? = null
    ) : BaseSpec

    data class Status(
        val certificate: List<Byte>? = null,
        val conditions: List<SigningRequestCondition>? = null
    ) : BaseSpec
}