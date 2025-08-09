package io.violabs.picard.v2.resources.authorization.role

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleListTest : FullBuildSim<ClusterRoleList, ClusterRoleListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleList, ClusterRoleListDslBuilder> {
            scenario {
                id = "minimum"
                given(ClusterRoleListDslBuilder()) {
                    items {
                        clusterRole {  }
                    }
                }
                expected = ClusterRoleList(
                    items = listOf(ClusterRole())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleList, ClusterRoleListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ClusterRoleListDslBuilder())
            }
        }
    }
}