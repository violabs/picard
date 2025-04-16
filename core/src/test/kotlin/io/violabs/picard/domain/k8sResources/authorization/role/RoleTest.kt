package io.violabs.picard.domain.k8sResources.authorization.role


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleTest : SuccessBuildSim<Role, Role.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Role, Role.Builder> {
            scenario {
                id = "minimum"
                given(Role.Builder())
                expected = Role()
            }

            scenario {
                id = "full"
                given(Role.Builder()) {
                    rules {
                        sharedPolicyRule()
                    }

                    sharedMetadata()
                }
                expected = Role(
                    rules = listOf(POLICY_RULE),
                    metadata = METADATA
                )
            }
        }
    }
}