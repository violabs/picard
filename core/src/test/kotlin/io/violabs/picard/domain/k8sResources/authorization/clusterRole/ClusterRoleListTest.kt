package io.violabs.picard.domain.k8sResources.authorization.clusterRole

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleListTest : FullBuildSim<ClusterRoleList, ClusterRoleList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleList, ClusterRoleList.Builder> {
            scenario {
                id = "minimum"
                given(ClusterRoleList.Builder()) {
                    items {
                        role { }
                    }
                }
                expected = ClusterRoleList(
                    items = listOf(ClusterRole())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleList, ClusterRoleList.Builder> {
            requireNotEmptyScenario("items") {
                given(ClusterRoleList.Builder())
            }
        }
    }
}