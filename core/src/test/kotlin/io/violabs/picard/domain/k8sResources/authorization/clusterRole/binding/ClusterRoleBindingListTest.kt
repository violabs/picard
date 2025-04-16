package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleBindingListTest : FullBuildSim<ClusterRoleBindingList, ClusterRoleBindingList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleBindingList, ClusterRoleBindingList.Builder> {
            scenario {
                id = "minimum"
                given(ClusterRoleBindingList.Builder()) {
                    items {
                        binding {
                            roleRef { sharedRoleRef() }
                        }
                    }
                    sharedMetadata()
                }
                expected = ClusterRoleBindingList(
                    items = listOf(
                        ClusterRoleBinding(
                            roleRef = ROLE_REF
                        )
                    ),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleBindingList, ClusterRoleBindingList.Builder> {
            requireNotEmptyScenario("items") {
                given(ClusterRoleBindingList.Builder())
            }
        }
    }
}