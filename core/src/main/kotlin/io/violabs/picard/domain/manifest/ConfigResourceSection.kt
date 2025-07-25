package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMap
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapList
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.domain.k8sResources.config.secret.SecretList

interface ConfigResource<T : APIVersion, META> : K8sResource<T, META>
interface ConfigListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ConfigResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ConfigResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ConfigListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ConfigResourceSection> {

        fun configMap(block: ConfigMap.Builder.() -> Unit) {
            val configMap = ConfigMap.Builder().apply(block).build()
            resources.add(configMap)
        }

        fun configMapList(block: ConfigMapList.Builder.() -> Unit) {
            val list = ConfigMapList.Builder().apply(block).build()
            lists.add(list)
        }

        fun secret(block: Secret.Builder.() -> Unit) {
            val secret = Secret.Builder().apply(block).build()
            resources.add(secret)
        }

        fun secretList(block: SecretList.Builder.() -> Unit) {
            val list = SecretList.Builder().apply(block).build()
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