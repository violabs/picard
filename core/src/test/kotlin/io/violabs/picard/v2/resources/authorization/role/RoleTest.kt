package io.violabs.picard.v2.resources.authorization.role


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleTest : SuccessBuildSim<Role, RoleDslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Role, RoleDslBuilder> {
            scenario {
                id = "minimum"
                given(RoleDslBuilder())
                expected = Role()
            }

            scenario {
                id = "full"
                given(RoleDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    rules {
                        sharedPolicyRule()
                    }
                }
                expected = Role(
                    metadata = Common.OBJECT_META,
                    rules = listOf(policyRule)
                )
            }
        }
    }
}