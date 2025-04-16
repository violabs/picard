package io.violabs.picard.domain.k8sResources.authorization.role


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleListTest : FullBuildSim<RoleList, RoleList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleList, RoleList.Builder> {
            scenario {
                id = "minimum"
                given(RoleList.Builder()) {
                    items {
                        role {  }
                    }

                    sharedMetadata()
                }
                expected = RoleList(
                    items = listOf(Role()),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleList, RoleList.Builder> {
            requireNotEmptyScenario("items") {
                given(RoleList.Builder())
            }
        }
    }
}