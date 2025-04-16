package io.violabs.picard.domain.k8sResources.authorization.clusterRole


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.authorization.AggregationRule
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleTest : SuccessBuildSim<ClusterRole, ClusterRole.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRole, ClusterRole.Builder> {
            scenario {
                id = "minimum"
                given(ClusterRole.Builder())
                expected = ClusterRole()
            }

            scenario {
                id = "full"
                given(ClusterRole.Builder()) {
                    sharedMetadata()

                    aggregationRule {
                        clusterRoleSelectors {
                            selector { sharedSelector() }
                        }
                    }

                    rules {
                        sharedPolicyRule()
                    }
                }
                expected = ClusterRole(
                    metadata = METADATA,
                    aggregationRule = AggregationRule(
                        clusterRoleSelectors = listOf(LABEL_SELECTOR)
                    ),
                    rules = listOf(POLICY_RULE)
                )
            }
        }
    }
}