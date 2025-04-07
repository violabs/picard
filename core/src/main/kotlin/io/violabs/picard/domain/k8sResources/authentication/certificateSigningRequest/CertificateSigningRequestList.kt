package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CertificateSigningRequestList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val items: List<CertificateSigningRequest>,
    override val metadata: ListMeta? = null
) : K8sListResource<CertificateSigningRequestList.Version, CertificateSigningRequest> {
    interface Version : APIVersion
}
