package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassDslBuilder
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassDslBuilderScope
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassListDslBuilder
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassListDslBuilderScope
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionDslBuilder
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionDslBuilderScope
import io.violabs.picard.v2.resources.extend.webhook.mutating.MutatingWebhookConfigurationDslBuilder
import io.violabs.picard.v2.resources.extend.webhook.mutating.MutatingWebhookConfigurationDslBuilderScope
import io.violabs.picard.v2.resources.extend.webhook.validating.ValidatingWebhookConfigurationDslBuilder
import io.violabs.picard.v2.resources.extend.webhook.validating.ValidatingWebhookConfigurationDslBuilderScope

interface ExtendResource<T : APIVersion, META> : K8sResource<T, META>
interface ExtendListResource<T : APIVersion, E> : K8sListResource<T, E>

data class ExtendResourceSection(
    override val resources: List<K8sAPIResource<*>>
) : ManifestResource {

    class Builder(
        private val resources: MutableList<ExtendResource<*, *>> = mutableListOf(),
        private val lists: MutableList<ExtendListResource<*, *>> = mutableListOf()
    ) : DslBuilder<ExtendResourceSection> {
        fun customResourceDefinition(block: CustomResourceDefinitionDslBuilderScope) {
            val crd = CustomResourceDefinitionDslBuilder().apply(block).build()
            resources.add(crd)
        }

        fun deviceClass(block: DeviceClassDslBuilderScope) {
            val deviceClass = DeviceClassDslBuilder().apply(block).build()
            resources.add(deviceClass)
        }

        fun deviceClassList(block: DeviceClassListDslBuilderScope) {
            val list = DeviceClassListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun mutatingWebhookConfiguration(block: MutatingWebhookConfigurationDslBuilderScope) {
            val config = MutatingWebhookConfigurationDslBuilder().apply(block).build()
            resources.add(config)
        }

        fun validatingWebhookConfiguration(block: ValidatingWebhookConfigurationDslBuilderScope) {
            val config = ValidatingWebhookConfigurationDslBuilder().apply(block).build()
            resources.add(config)
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