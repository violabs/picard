package io.violabs.picard.v2.resources.authorization.role.binding


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authorization.role.binding.RoleBinding
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.authorization.role.RoleTestConfig
import org.junit.jupiter.api.BeforeAll

class RoleBindingTest : FullBuildSim<RoleBindingV2, RoleBindingV2DslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleBindingV2, RoleBindingV2DslBuilder> {
            scenario {
                id = "minimum"
                given(RoleBindingV2DslBuilder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = RoleBindingV2(
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
                expected = RoleBindingV2(
                    roleRef = roleRef,
                    subjects = listOf(subject),
                    metadata = Common.OBJECT_META
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleBindingV2, RoleBindingV2DslBuilder> {
            requireScenario("roleRef") {
                given(RoleBindingV2DslBuilder())
            }
        }
    }
}