package io.violabs.picard.domain.k8sResources.config.secret

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class SecretList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Secret>,
    override val metadata: ListMeta? = null
) : K8sListResource<SecretList.Version, Secret> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Secret,
        Secret.Builder,
        Secret.Group,
        SecretList
        >(Secret.Group()) {

        override fun build(): SecretList {
            return SecretList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}