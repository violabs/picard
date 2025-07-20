package io.violabs.picard.domain.k8sResources.policy.flowSchema

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyListResource

data class FlowSchemaList(
    override val apiVersion: Version = KAPIVersion.FlowControlApiServerV1,
    override val items: List<FlowSchema>,
    override val metadata: ListMeta? = null
) : PolicyListResource<FlowSchemaList.Version, FlowSchema> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        FlowSchema,
        FlowSchema.Builder,
        FlowSchema.Group,
        FlowSchemaList
        >(FlowSchema.Group()) {

        override fun build(): FlowSchemaList {
            return FlowSchemaList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
