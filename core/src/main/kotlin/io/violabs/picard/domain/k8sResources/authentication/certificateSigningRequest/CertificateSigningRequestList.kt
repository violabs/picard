package io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.DSLBuilder
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

    class Builder : DSLBuilder<CertificateSigningRequestList> {
        private var items: List<CertificateSigningRequest>? = null
        private var metadata: ListMeta? = null

        fun items(scope: CertificateSigningRequest.Group.() -> Unit) {
            items = CertificateSigningRequest.Group().apply(scope).listItems()
        }

        fun metadata(scope: ListMeta.Builder.() -> Unit) {
            metadata = ListMeta.Builder().apply(scope).build()
        }

        override fun build(): CertificateSigningRequestList {
            return CertificateSigningRequestList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
