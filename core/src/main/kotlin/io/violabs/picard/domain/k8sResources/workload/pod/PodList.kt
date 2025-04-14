package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class PodList(
    override val apiVersion: Version = KAPIVersion.V1,
    override val items: List<Pod>,
    override val metadata: ListMeta? = null
) : K8sListResource<PodList.Version, Pod> {
    interface Version : APIVersion

    class Builder : DslBuilder<PodList> {
        private var _items: List<Pod>? = null
        private var metadata: ListMeta? = null

        fun items(): List<Pod>? = _items

        fun items(scope: PodGroup.() -> Unit) {
            _items = PodGroup().apply(scope).pods()
        }

        fun metadata(scope: ListMeta.Builder.() -> Unit) {
            metadata = ListMeta.Builder().apply(scope).build()
        }

        override fun build(): PodList {
            return PodList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }

    class PodGroup : BuilderGroup<Pod, Pod.Builder>(Pod.Builder()) {
        fun pods(): List<Pod>? = items()

        fun pod(scope: Pod.Builder.() -> Unit) {
            add(scope)
        }
    }
}