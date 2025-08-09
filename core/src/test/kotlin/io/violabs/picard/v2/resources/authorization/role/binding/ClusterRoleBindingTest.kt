package io.violabs.picard.v2.resources.authorization.role.binding


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.role.RoleTestConfig
import org.junit.jupiter.api.BeforeAll

class ClusterRoleBindingTest : FullBuildSim<ClusterRoleBinding, ClusterRoleBindingDslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleBinding, ClusterRoleBindingDslBuilder> {
            scenario {
                id = "minimum"
                given(ClusterRoleBindingDslBuilder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = ClusterRoleBinding(
                    roleRef = roleRef
                )
            }

            scenario {
                id = "full"
                given(ClusterRoleBindingDslBuilder()) {
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

                expected = ClusterRoleBinding(
                    roleRef = roleRef,
                    subjects = listOf(subject),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleBinding, ClusterRoleBindingDslBuilder> {
            requireScenario("roleRef") {
                given(ClusterRoleBindingDslBuilder())
            }
        }
    }
}