package io.violabs.picard.domain.k8sResources.authorization.role.binding


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RoleBindingTest : FullBuildSim<RoleBinding, RoleBinding.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<RoleBinding, RoleBinding.Builder> {
            scenario {
                id = "minimum"
                given(RoleBinding.Builder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = RoleBinding(
                    roleRef = ROLE_REF
                )
            }

            scenario {
                id = "full"
                given(RoleBinding.Builder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                    subjects {
                        sharedSubject()
                    }
                    sharedMetadata()
                }
                expected = RoleBinding(
                    roleRef = ROLE_REF,
                    subjects = listOf(SUBJECT),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<RoleBinding, RoleBinding.Builder> {
            requireScenario("roleRef") {
                given(RoleBinding.Builder())
            }
        }
    }
}