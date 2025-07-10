package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequest
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequestList
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundle
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundleList
import io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview.SelfSubjectReview
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequestList
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReview
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReviewList

interface AuthenticationResource<T : APIVersion> : K8sResource<T>
interface AuthenticationListResource<T : APIVersion, E> : K8sListResource<T, E>

data class AuthenticationResourceSection(
    override val resources: List<K8sAPIResource<*>>
): ManifestResource {

    class Builder(
        private val resources: MutableList<AuthenticationResource<*>> = mutableListOf(),
        private val lists: MutableList<AuthenticationListResource<*, *>> = mutableListOf()
    ) : DslBuilder<AuthenticationResourceSection> {

        fun certificateSigningRequest(block: CertificateSigningRequest.Builder.() -> Unit) {
            val request = CertificateSigningRequest.Builder().apply(block).build()
            resources.add(request)
        }

        fun certificateSigningRequestList(block: CertificateSigningRequestList.Builder.() -> Unit) {
            val list = CertificateSigningRequestList.Builder().apply(block).build()
            lists.add(list)
        }

        fun clusterTrustBundle(block: ClusterTrustBundle.Builder.() -> Unit) {
            val trustBundle = ClusterTrustBundle.Builder().apply(block).build()
            resources.add(trustBundle)
        }

        fun clusterTrustBundleList(block: ClusterTrustBundleList.Builder.() -> Unit) {
            val list = ClusterTrustBundleList.Builder().apply(block).build()
            lists.add(list)
        }

        fun selfSubjectReview(block: SelfSubjectReview.Builder.() -> Unit) {
            val selfSubjectReview = SelfSubjectReview.Builder().apply(block).build()
            resources.add(selfSubjectReview)
        }

        fun tokenRequest(block: TokenRequest.Builder.() -> Unit) {
            val tokenRequest = TokenRequest.Builder().apply(block).build()
            resources.add(tokenRequest)
        }

        fun tokenRequestList(block: TokenRequestList.Builder.() -> Unit) {
            val list = TokenRequestList.Builder().apply(block).build()
            lists.add(list)
        }

        fun tokenReview(block: TokenReview.Builder.() -> Unit) {
            val tokenReview = TokenReview.Builder().apply(block).build()
            resources.add(tokenReview)
        }

        fun tokenReviewList(block: TokenReviewList.Builder.() -> Unit) {
            val list = TokenReviewList.Builder().apply(block).build()
            lists.add(list)
        }

        override fun build(): AuthenticationResourceSection {
            return AuthenticationResourceSection(
                vRequireNotEmpty(
                    (resources + lists).sortedBy { it::class.simpleName },
                    "resources"
                ))
        }
    }
}