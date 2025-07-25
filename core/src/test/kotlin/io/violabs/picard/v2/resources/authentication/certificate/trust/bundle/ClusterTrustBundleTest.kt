package io.violabs.picard.v2.resources.authentication.certificate.trust.bundle


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleTest : FullBuildSim<ClusterTrustBundleV2, ClusterTrustBundleV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterTrustBundleV2, ClusterTrustBundleV2DslBuilder> {
            scenario {
                id = "minimum"
                description = "included metadata"
                given(ClusterTrustBundleV2DslBuilder()) {
                    spec {
                        trustBundle = PLACEHOLDER
                    }
                }
                expected = ClusterTrustBundleV2(
                    spec = ClusterTrustBundleSpec(
                        trustBundle = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                description = "exclude full metadata"
                given(ClusterTrustBundleV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        trustBundle = PLACEHOLDER
                        signerName = PLACEHOLDER
                    }
                }
                expected = ClusterTrustBundleV2(
                    spec = ClusterTrustBundleSpec(
                        trustBundle = PLACEHOLDER,
                        signerName = PLACEHOLDER
                    ),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundleV2, ClusterTrustBundleV2DslBuilder> {
            requireScenario("spec") {
                given(ClusterTrustBundleV2DslBuilder())
            }
        }
    }
}