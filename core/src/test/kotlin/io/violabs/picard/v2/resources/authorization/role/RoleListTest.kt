package io.violabs.picard.v2.resources.authorization.role

import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleListTest : FullBuildSim<RoleList, RoleListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleList, RoleListDslBuilder> {
            scenario {
                id = "minimum"
                given(RoleListDslBuilder()) {
                    items {
                        role {}
                    }

                    metadata { sharedListMeta() }
                }
                expected = RoleList(
                    items = listOf(Role()),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleList, RoleListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(RoleListDslBuilder())
            }
        }
    }
}