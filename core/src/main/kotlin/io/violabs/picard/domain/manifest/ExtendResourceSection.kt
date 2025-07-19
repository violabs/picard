package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinition
import io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition.CustomResourceDefinitionList
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClass
import io.violabs.picard.domain.k8sResources.extend.deviceClass.DeviceClassList
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig.MutatingWebhookConfigurationList
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfiguration
import io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig.ValidatingWebhookConfigurationList

interface ExtendResource<T : APIVersion, META> : K8sResource<T, META>
interface ExtendListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ExtendResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ExtendResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ExtendListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ExtendResourceSection> {
        fun customResourceDefinition(block: CustomResourceDefinition.Builder.() -> Unit) {
            resources += CustomResourceDefinition.Builder().apply(block).build()
        }

        fun customResourceDefinitionList(block: CustomResourceDefinitionList.Builder.() -> Unit) {
            val list = CustomResourceDefinitionList.Builder().apply(block).build()
            lists.add(list)
        }

        fun deviceClass(block: DeviceClass.Builder.() -> Unit) {
            val resource = DeviceClass.Builder().apply(block).build()
            resources.add(resource)
        }

        fun deviceClassList(block: DeviceClassList.Builder.() -> Unit) {
            val list = DeviceClassList.Builder().apply(block).build()
            lists.add(list)
        }

        fun mutatingWebhook(block: MutatingWebhookConfiguration.Builder.() -> Unit) {
            val resource = MutatingWebhookConfiguration.Builder().apply(block).build()
            resources.add(resource)
        }

        fun mutatingWebhookList(block: MutatingWebhookConfigurationList.Builder.() -> Unit) {
            val list = MutatingWebhookConfigurationList.Builder().apply(block).build()
            lists.add(list)
        }

        fun validatingWebhook(block: ValidatingWebhookConfiguration.Builder.() -> Unit) {
            val resource = ValidatingWebhookConfiguration.Builder().apply(block).build()
            resources.add(resource)
        }

        fun validatingWebhookList(block: ValidatingWebhookConfigurationList.Builder.() -> Unit) {
            val list = ValidatingWebhookConfigurationList.Builder().apply(block).build()
            lists.add(list)
        }

        override fun build(): ExtendResourceSection {
            return ExtendResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                )
            )
        }
    }
}