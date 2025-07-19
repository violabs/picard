package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageResource

data class CSIDriver(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : StorageResource<CSIDriver.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val attachRequired: Boolean,
        val podInfoOnMount: Boolean,
        val requiresRepublish: Boolean,
        val seLinuxMount: Boolean,
        val storageCapacity: Int,
        val fsGroupPolicy: String,
        val tokenRequests: List<CSIDriverTokenRequest>,
        val volumeLifecycleModes: List<String>
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var attachRequired: Boolean? = null
            private var podInfoOnMount: Boolean? = null
            private var requiresRepublish: Boolean? = null
            private var seLinuxMount: Boolean? = null
            var storageCapacity: Int? = null
            var fsGroupPolicy: String? = null
            private var tokenRequests: List<CSIDriverTokenRequest>? = null
            private var volumeLifecycleModes: List<String>? = null

            fun attachRequired(value: Boolean = true) {
                attachRequired = value
            }

            fun podInfoOnMount(value: Boolean = true) {
                podInfoOnMount = value
            }

            fun requiresRepublish(value: Boolean = true) {
                requiresRepublish = value
            }

            fun seLinuxMount(value: Boolean = true) {
                seLinuxMount = value
            }

            fun tokenRequests(scope: CSIDriverTokenRequest.Group.() -> Unit) {
                tokenRequests = CSIDriverTokenRequest.Group().apply(scope).requests()
            }

            fun volumeLifecycleModes(vararg modes: String) {
                volumeLifecycleModes = modes.toList()
            }

            override fun build(): Spec {
                return Spec(
                    attachRequired = vRequireNotNull(this::attachRequired),
                    podInfoOnMount = vRequireNotNull(this::podInfoOnMount),
                    requiresRepublish = vRequireNotNull(this::requiresRepublish),
                    seLinuxMount = vRequireNotNull(this::seLinuxMount),
                    storageCapacity = vRequireNotNull(this::storageCapacity),
                    fsGroupPolicy = vRequireNotNull(this::fsGroupPolicy),
                    tokenRequests = vRequireNotNull(this::tokenRequests),
                    volumeLifecycleModes = vRequireNotEmpty(this::volumeLifecycleModes)
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<CSIDriver, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): CSIDriver {
            return CSIDriver(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CSIDriver, Builder>(Builder()) {
        fun csiDriverItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}