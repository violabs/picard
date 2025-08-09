package io.violabs.picard.v2.resources.authentication.cluster

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleTest : FullBuildSim<ClusterTrustBundle, ClusterTrustBundleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterTrustBundle, ClusterTrustBundleDslBuilder> {
            scenario {
                id = "minimum"
                description = "included metadata"
                given(ClusterTrustBundleDslBuilder()) {
                    spec {
                        trustBundle = PLACEHOLDER
                    }
                }
                expected = ClusterTrustBundle(
                    spec = ClusterTrustBundleSpec(
                        trustBundle = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                description = "exclude full metadata"
                given(ClusterTrustBundleDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        trustBundle = PLACEHOLDER
                        signerName = PLACEHOLDER
                    }
                }
                expected = ClusterTrustBundle(
                    spec = ClusterTrustBundleSpec(
                        trustBundle = PLACEHOLDER,
                        signerName = PLACEHOLDER
                    ),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundle, ClusterTrustBundleDslBuilder> {
            requireScenario("spec") {
                given(ClusterTrustBundleDslBuilder())
            }
        }
    }
}