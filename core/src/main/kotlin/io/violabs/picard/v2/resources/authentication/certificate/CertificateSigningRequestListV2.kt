package io.violabs.picard.v2.resources.authentication.certificate

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class CertificateSigningRequestListV2(
    override val apiVersion: Version = KAPIVersion.CertificatesV1,
    override val items: List<CertificateSigningRequestV2>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<CertificateSigningRequestListV2.Version, CertificateSigningRequestV2> {
    interface Version : APIVersion
}
