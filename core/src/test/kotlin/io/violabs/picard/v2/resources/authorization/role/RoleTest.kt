package io.violabs.picard.v2.resources.authorization.role


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleTest : SuccessBuildSim<Role, RoleV2DslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Role, RoleV2DslBuilder> {
            scenario {
                id = "minimum"
                given(RoleV2DslBuilder())
                expected = Role()
            }

            scenario {
                id = "full"
                given(RoleV2DslBuilder()) {
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