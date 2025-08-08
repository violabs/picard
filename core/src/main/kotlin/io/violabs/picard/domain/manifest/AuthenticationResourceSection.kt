package io.violabs.picard.domain.manifest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sAPIResource
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestDslBuilder
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestDslBuilderScope
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestListDslBuilder
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestListDslBuilderScope
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundleDslBuilder
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundleDslBuilderScope
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundleListDslBuilder
import io.violabs.picard.v2.resources.authentication.cluster.ClusterTrustBundleListDslBuilderScope
import io.violabs.picard.v2.resources.authentication.self.SelfSubjectReviewDslBuilder
import io.violabs.picard.v2.resources.authentication.self.SelfSubjectReviewDslBuilderScope
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestDslBuilder
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestDslBuilderScope
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestListDslBuilder
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestListDslBuilderScope
import io.violabs.picard.v2.resources.authentication.token.review.TokenReviewDslBuilder
import io.violabs.picard.v2.resources.authentication.token.review.TokenReviewDslBuilderScope
import io.violabs.picard.v2.resources.authentication.token.review.TokenReviewListDslBuilder
import io.violabs.picard.v2.resources.authentication.token.review.TokenReviewListDslBuilderScope

interface AuthenticationResource<T : APIVersion, META> : K8sResource<T, META>
interface AuthenticationListResource<T : APIVersion, E> : K8sListResource<T, E>

data class AuthenticationResourceSection(
    override val resources: List<K8sAPIResource<*>>
): ManifestResource {

    class Builder(
        private val resources: MutableList<AuthenticationResource<*, *>> = mutableListOf(),
        private val lists: MutableList<AuthenticationListResource<*, *>> = mutableListOf()
    ) : DslBuilder<AuthenticationResourceSection> {

        fun certificateSigningRequest(block: CertificateSigningRequestDslBuilderScope) {
            val request = CertificateSigningRequestDslBuilder().apply(block).build()
            resources.add(request)
        }

        fun certificateSigningRequestList(block: CertificateSigningRequestListDslBuilderScope) {
            val list = CertificateSigningRequestListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun clusterTrustBundle(block: ClusterTrustBundleDslBuilderScope) {
            val trustBundle = ClusterTrustBundleDslBuilder().apply(block).build()
            resources.add(trustBundle)
        }

        fun clusterTrustBundleList(block: ClusterTrustBundleListDslBuilderScope) {
            val list = ClusterTrustBundleListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun selfSubjectReview(block: SelfSubjectReviewDslBuilderScope) {
            val selfSubjectReview = SelfSubjectReviewDslBuilder().apply(block).build()
            resources.add(selfSubjectReview)
        }

        fun tokenRequest(block: TokenRequestDslBuilderScope) {
            val tokenRequest = TokenRequestDslBuilder().apply(block).build()
            resources.add(tokenRequest)
        }

        fun tokenRequestList(block: TokenRequestListDslBuilderScope) {
            val list = TokenRequestListDslBuilder().apply(block).build()
            lists.add(list)
        }

        fun tokenReview(block: TokenReviewDslBuilderScope) {
            val tokenReview = TokenReviewDslBuilder().apply(block).build()
            resources.add(tokenReview)
        }

        fun tokenReviewList(block: TokenReviewListDslBuilderScope) {
            val list = TokenReviewListDslBuilder().apply(block).build()
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