package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.role.ClusterRoleTest.Companion.sharedRoleRef
import io.violabs.picard.v2.resources.authorization.role.RoleTestConfig
import org.junit.jupiter.api.BeforeAll

class ClusterRoleBindingListTest : FullBuildSim<ClusterRoleBindingList, ClusterRoleBindingListDslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleBindingList, ClusterRoleBindingListDslBuilder> {
            scenario {
                id = "minimum"
                given(ClusterRoleBindingListDslBuilder()) {
                    items {
                        clusterRoleBinding {
                            roleRef {
                                sharedRoleRef()
                            }
                        }
                    }

                    metadata { sharedListMeta() }
                }
                expected = ClusterRoleBindingList(
                    items = listOf(
                        ClusterRoleBinding(
                            roleRef = roleRef
                        )
                    ),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleBindingList, ClusterRoleBindingListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ClusterRoleBindingListDslBuilder())
            }
        }
    }
}