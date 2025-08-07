package io.violabs.picard.v2.resources.authentication.cluster


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundle
import io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle.ClusterTrustBundleList
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleListTest : FullBuildSim<ClusterTrustBundleList, ClusterTrustBundleList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterTrustBundleList, ClusterTrustBundleList.Builder> {
            scenario {
                id = "minimum"
                description = "with blank metadata"
                given(ClusterTrustBundleList.Builder()) {
                    items {
                        bundle {
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
                            spec = ClusterTrustBundle.Spec(
                                trustBundle = PLACEHOLDER
                            )
                        )
                    ),
                    metadata = ListMeta()
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundleList, ClusterTrustBundleList.Builder> {
            requireNotEmptyScenario("items") {
                given(ClusterTrustBundleList.Builder())
            }
        }
    }
}