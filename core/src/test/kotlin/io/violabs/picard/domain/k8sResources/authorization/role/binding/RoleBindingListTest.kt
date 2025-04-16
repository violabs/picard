package io.violabs.picard.domain.k8sResources.authorization.role.binding


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleBindingListTest : FullBuildSim<RoleBindingList, RoleBindingList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleBindingList, RoleBindingList.Builder> {
            scenario {
                id = "minimum"
                given(RoleBindingList.Builder()) {
                    items {
                        binding {
                            roleRef { sharedRoleRef() }
                        }
                    }
                    sharedMetadata()
                }
                expected = RoleBindingList(
                    items = listOf(
                        RoleBinding(
                            roleRef = ROLE_REF
                        )
                    ),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleBindingList, RoleBindingList.Builder> {
            requireNotEmptyScenario("items") {
                given(RoleBindingList.Builder())
            }
        }
    }
}