package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource

data class ClusterTrustBundle(
    override val apiVersion: Version = KAPIVersion.CertificatesV1Alpha1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null
) : AuthenticationResource<ClusterTrustBundle.Version> {
    interface Version : APIVersion

    data class Spec(
        val trustBundle: String,
        val signerName: String? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var trustBundle: String? = null
            var signerName: String? = null

            override fun build(): Spec {
                return Spec(
                    trustBundle = vRequireNotNull(this::trustBundle),
                    signerName = signerName
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<ClusterTrustBundle, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ClusterTrustBundle {
            return ClusterTrustBundle(
                spec = vRequireNotNull(this::spec),
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ClusterTrustBundle, Builder>(Builder()) {
        fun bundle(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}