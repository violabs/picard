package io.violabs.picard.domain.k8sResources.cluster.lease.candidate

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import java.time.Instant

data class LeaseCandidate(
    override val apiVersion: Version = KAPIVersion.CoordinationV1Alpha1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null
) : ClusterResource<LeaseCandidate.Version> {
    interface Version : APIVersion

    data class Spec(
        val leaseName: String,
        val preferredStrategies: List<String>,
        val binaryVersion: String? = null,
        val emulationVersion: String? = null,
        val pingTime: Instant? = null,
        val renewTime: Instant? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var leaseName: String? = null
            private var preferredStrategies: List<String>? = null
            var binaryVersion: String? = null
            var emulationVersion: String? = null
            var pingTime: Instant? = null
            var renewTime: Instant? = null

            fun preferredStrategies(vararg strategies: String) {
                this.preferredStrategies = strategies.toList()
            }

            override fun build(): Spec {
                return Spec(
                    leaseName = vRequireNotNull(this::leaseName),
                    preferredStrategies = vRequireNotEmpty(this::preferredStrategies),
                    binaryVersion = binaryVersion,
                    emulationVersion = emulationVersion,
                    pingTime = pingTime,
                    renewTime = renewTime,
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<LeaseCandidate, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): LeaseCandidate {
            return LeaseCandidate(
                spec = vRequireNotNull(this::spec), metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<LeaseCandidate, Builder>(Builder()) {
        fun candidate(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}