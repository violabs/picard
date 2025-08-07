package io.violabs.picard.v2.resources.authorization.role.binding

import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleBindingListTest : FullBuildSim<RoleBindingList, RoleBindingListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleBindingListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleBindingList, RoleBindingListDslBuilder> {
            scenario {
                id = "minimum"
                given(RoleBindingListDslBuilder()) {
                    items {
                        roleBinding {
                            roleRef { sharedRoleRef() }
                        }
                    }

                    metadata { sharedListMeta() }
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

        private val FAILURE_POSSIBILITIES = possibilities<RoleBindingList, RoleBindingListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(RoleBindingListDslBuilder())
            }
        }
    }
}