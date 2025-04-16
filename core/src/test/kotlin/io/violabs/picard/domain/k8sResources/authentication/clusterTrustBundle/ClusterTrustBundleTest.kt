package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleTest : FullBuildSim<ClusterTrustBundle, ClusterTrustBundle.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterTrustBundle, ClusterTrustBundle.Builder> {
            scenario {
                id = "minimum"
                description = "included metadata"
                given(ClusterTrustBundle.Builder()) {
                    spec {
                        trustBundle = PLACEHOLDER
                    }
                }
                expected = ClusterTrustBundle(
                    spec = ClusterTrustBundle.Spec(
                        trustBundle = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                description = "exclude full metadata"
                given(ClusterTrustBundle.Builder()) {
                    spec {
                        trustBundle = PLACEHOLDER
                        signerName = PLACEHOLDER
                    }

                    sharedMetadata()
                }
                expected = ClusterTrustBundle(
                    spec = ClusterTrustBundle.Spec(
                        trustBundle = PLACEHOLDER,
                        signerName = PLACEHOLDER
                    ),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundle, ClusterTrustBundle.Builder> {
            requireScenario("spec") {
                given(ClusterTrustBundle.Builder())
            }
        }
    }
}