package io.violabs.picard.v2.resources.authentication.cluster


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleListTest : FullBuildSim<ClusterTrustBundleList, ClusterTrustBundleListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterTrustBundleList, ClusterTrustBundleListDslBuilder> {
            scenario {
                id = "minimum"
                description = "with blank metadata"
                given(ClusterTrustBundleListDslBuilder()) {
                    items {
                        clusterTrustBundle {
                            spec {
                                trustBundle = PLACEHOLDER
                            }
                        }
                    }

                    metadata {  }
                }
                expected = ClusterTrustBundleList(
                    items = listOf(
                        ClusterTrustBundle(
                            spec = ClusterTrustBundleSpec(
                                trustBundle = PLACEHOLDER
                            )
                        )
                    ),
                    metadata = ListMeta()
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundleList, ClusterTrustBundleListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ClusterTrustBundleListDslBuilder())
            }
        }
    }
}