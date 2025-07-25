package io.violabs.picard.domain.k8sResources.config.configMap

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigListResource

data class ConfigMapList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<ConfigMap>,
    override val metadata: ListMeta? = null
) : ConfigListResource<ConfigMapList.Version, ConfigMap> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ConfigMap,
        ConfigMap.Builder,
        ConfigMap.Group,
        ConfigMapList>(ConfigMap.Group()) {

        override fun build(): ConfigMapList {
            return ConfigMapList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}