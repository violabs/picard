package io.violabs.picard.domain.k8sResources.authorization.clusterRole.binding


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleBindingTest : FullBuildSim<ClusterRoleBinding, ClusterRoleBinding.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleBindingTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRoleBinding, ClusterRoleBinding.Builder> {
            scenario {
                id = "minimum"
                given(ClusterRoleBinding.Builder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                }
                expected = ClusterRoleBinding(
                    roleRef = ROLE_REF
                )
            }

            scenario {
                id = "full"
                given(ClusterRoleBinding.Builder()) {
                    roleRef {
                        sharedRoleRef()
                    }
                    subjects {
                        sharedSubject()
                    }
                    sharedMetadata()
                }

                expected = ClusterRoleBinding(
                    roleRef = ROLE_REF,
                    subjects = listOf(SUBJECT),
                    metadata = METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ClusterRoleBinding, ClusterRoleBinding.Builder> {
            requireScenario("roleRef") {
                given(ClusterRoleBinding.Builder())
            }
        }
    }
}