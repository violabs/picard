package io.violabs.picard.v2.resources.authorization.role.binding


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.role.RoleTestConfig
import org.junit.jupiter.api.BeforeAll

class ClusterRoleBindingTest : FullBuildSim<ClusterRoleBindingV2, ClusterRoleBindingV2DslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleBindingV2, ClusterRoleBindingV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ClusterRoleBindingV2DslBuilder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = ClusterRoleBindingV2(
                    roleRef = roleRef
                )
            }

            scenario {
                id = "full"
                given(ClusterRoleBindingV2DslBuilder()) {
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

                expected = ClusterRoleBindingV2(
                    roleRef = roleRef,
                    subjects = listOf(subject),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleBindingV2, ClusterRoleBindingV2DslBuilder> {
            requireScenario("roleRef") {
                given(ClusterRoleBindingV2DslBuilder())
            }
        }
    }
}