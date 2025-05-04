package io.violabs.picard.domain.k8sResources.cluster.lease

import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.common.ResourceSpecDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import java.time.Instant

data class Lease(
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<Lease.Version> {
    interface Version : APIVersion

    data class Spec(
        val acquireTime: Instant? = null,
        val holderIdentity: String? = null,
        val leaseDurationSeconds: Int? = null,
        val leaseTransitions: Int? = null,
        val preferredHolder: String? = null,
        val renewTime: Instant? = null,
        val strategy: String? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var acquireTime: Instant? = null
            var holderIdentity: String? = null
            var leaseDurationSeconds: Int? = null
            var leaseTransitions: Int? = null
            var preferredHolder: String? = null
            var renewTime: Instant? = null
            var strategy: String? = null

            override fun build(): Spec {
                return Spec(
                    acquireTime = acquireTime,
                    holderIdentity = holderIdentity,
                    leaseDurationSeconds = leaseDurationSeconds,
                    leaseTransitions = leaseTransitions,
                    preferredHolder = preferredHolder,
                    renewTime = renewTime,
                    strategy = strategy
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<Lease, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): Lease {
            return Lease(
                spec = spec,
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Lease, Builder>(Builder()) {
        fun lease(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}