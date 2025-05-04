package io.violabs.picard.domain.k8sResources.authentication.serviceAccount

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class ServiceAccountList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ServiceAccount>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<ServiceAccountList.Version, ServiceAccount> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ServiceAccount,
        ServiceAccount.Builder,
        ServiceAccount.Group,
        ServiceAccountList>(ServiceAccount.Group()) {

        override fun build(): ServiceAccountList {
            return ServiceAccountList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
