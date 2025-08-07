package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequest
import io.violabs.picard.domain.k8sResources.authentication.certificateSigningRequest.CertificateSigningRequestList
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundle
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundleList
import io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview.SelfSubjectReview
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequestList
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReview
import io.violabs.picard.domain.k8sResources.authentication.tokenReview.TokenReviewList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuthenticationResourceSectionTest :
    FullBuildSim<AuthenticationResourceSection, AuthenticationResourceSectionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuthenticationResourceSectionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val CERTIFICATE_SIGNING_REQUEST = CertificateSigningRequest(
            spec = CertificateSigningRequestSpec(
                request = BYTES,
                signerName = PLACEHOLDER
            )
        )

        private val CLUSTER_TRUST_BUNDLE = ClusterTrustBundle(
            spec = ClusterTrustBundleSpec(
                trustBundle = PLACEHOLDER
            )
        )

        private val TOKEN_REQUEST = TokenRequest()

        private val TOKEN_REVIEW = TokenReview(
            spec = TokenReviewSpec()
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<AuthenticationResourceSection, AuthenticationResourceSectionDslBuilder> {
                scenario {
                    id = "full"
                    given(AuthenticationResourceSectionDslBuilder()) {
                        certificateSigningRequest {
                            spec {
                                request(BYTE_1, BYTE_2)
                                signerName = PLACEHOLDER
                            }
                        }
                        certificateSigningRequestList {
                            items {
                                request {
                                    spec {
                                        request(BYTE_1, BYTE_2)
                                        signerName = PLACEHOLDER
                                    }
                                }
                            }
                        }
                        clusterTrustBundle {
                            spec {
                                trustBundle = PLACEHOLDER
                            }
                        }
                        clusterTrustBundleList {
                            items {
                                bundle {
                                    spec {
                                        trustBundle = PLACEHOLDER
                                    }
                                }
                            }
                        }
                        selfSubjectReview { }
                        tokenRequest { }
                        tokenRequestList {
                            items {
                                request { }
                            }
                        }
                        tokenReview {
                            spec {}
                        }
                        tokenReviewList {
                            items {
                                review {
                                    spec {}
                                }
                            }
                        }
                    }
                    expected = AuthenticationResourceSection(
                        resources = listOf(
                            CERTIFICATE_SIGNING_REQUEST,
                            CertificateSigningRequestList(items = listOf(CERTIFICATE_SIGNING_REQUEST)),
                            CLUSTER_TRUST_BUNDLE,
                            ClusterTrustBundleList(items = listOf(CLUSTER_TRUST_BUNDLE)),
                            SelfSubjectReview(),
                            TOKEN_REQUEST,
                            TokenRequestList(items = listOf(TOKEN_REQUEST)),
                            TOKEN_REVIEW,
                            TokenReviewList(items = listOf(TOKEN_REVIEW))
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<AuthenticationResourceSection, AuthenticationResourceSectionDslBuilder> {
                requireNotEmptyScenario("resources") {
                    given(AuthenticationResourceSectionDslBuilder())
                }
            }
    }
}