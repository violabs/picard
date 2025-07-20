package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.common.PicardDsl
import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class CertificateSigningRequestList(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val items: List<CertificateSigningRequest>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<CertificateSigningRequestList.Version, CertificateSigningRequest> {
    interface Version : APIVersion

    @PicardDsl
    class Builder : ResourceListDslBuilder<
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
