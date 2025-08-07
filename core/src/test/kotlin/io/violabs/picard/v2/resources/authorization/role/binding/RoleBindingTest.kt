package io.violabs.picard.v2.resources.authorization.role.binding


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.role.RoleTestConfig
import org.junit.jupiter.api.BeforeAll

class RoleBindingTest : FullBuildSim<RoleBinding, RoleBindingV2DslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleBinding, RoleBindingV2DslBuilder> {
            scenario {
                id = "minimum"
                given(RoleBindingV2DslBuilder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = RoleBinding(
                    roleRef = roleRef
                )
            }

            scenario {
                id = "full"
                given(RoleBindingV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    roleRef {
                        sharedRoleRef()
                    }
                    subjects {
                        sharedSubject()
                    }
                }
                expected = RoleBinding(
                    roleRef = roleRef,
                    subjects = listOf(subject),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleBinding, RoleBindingV2DslBuilder> {
            requireScenario("roleRef") {
                given(RoleBindingV2DslBuilder())
            }
        }
    }
}