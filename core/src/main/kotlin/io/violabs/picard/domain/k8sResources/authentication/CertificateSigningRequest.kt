package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class CertificateSigningRequest(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<CertificateSigningRequest.Version> {

    interface Version : APIVersion
}