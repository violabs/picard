package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class CertificateSigningRequestList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val items: List<CertificateSigningRequest>,
    override val metadata: ListMeta? = null
) : K8sListResource<CertificateSigningRequestList.Version, CertificateSigningRequest> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        CertificateSigningRequest,
        CertificateSigningRequest.Builder,
        CertificateSigningRequest.Group,
        CertificateSigningRequestList
        >(CertificateSigningRequest.Group()) {

        override fun build(): CertificateSigningRequestList {
            return CertificateSigningRequestList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
