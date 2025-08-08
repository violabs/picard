package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.config.map.ConfigMapDslBuilder
import io.violabs.picard.v2.resources.config.map.ConfigMapDslBuilderScope
import io.violabs.picard.v2.resources.config.map.ConfigMapListDslBuilder
import io.violabs.picard.v2.resources.config.map.ConfigMapListDslBuilderScope
import io.violabs.picard.v2.resources.config.secret.SecretDslBuilder
import io.violabs.picard.v2.resources.config.secret.SecretDslBuilderScope
import io.violabs.picard.v2.resources.config.secret.SecretListDslBuilder
import io.violabs.picard.v2.resources.config.secret.SecretListDslBuilderScope

interface ConfigResource<T : APIVersion, META> : K8sResource<T, META>
interface ConfigListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ConfigResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ConfigResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ConfigListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ConfigResourceSection> {

        fun configMap(block: ConfigMapDslBuilderScope) {
            val configMap = ConfigMapDslBuilder().apply(block).build()
            resources.add(configMap)
        }

        fun configMapList(block: ConfigMapListDslBuilderScope) {
            val list = ConfigMapListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun secret(block: SecretDslBuilderScope) {
            val secret = SecretDslBuilder().apply(block).build()
            resources.add(secret)
        }

        fun secretList(block: SecretListDslBuilderScope) {
            val list = SecretListDslBuilder().apply(block).build()
            lists.add(list)
        }

        override fun build(): ConfigResourceSection {
            return ConfigResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}