package io.violabs.picard.v2.resources.authorization.role


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterRoleTest : SuccessBuildSim<ClusterRole, ClusterRoleDslBuilder>() {
    companion object : RoleTestConfig {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterRoleTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ClusterRole, ClusterRoleDslBuilder> {
            scenario {
                id = "minimum"
                given(ClusterRoleDslBuilder())
                expected = ClusterRole()
            }

            scenario {
                id = "full"
                given(ClusterRoleDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    aggregationRule {
                        clusterRoleSelectors {
                            labelSelector {
                                sharedSelector()
                            }
                        }
                    }

                    rules {
                        sharedPolicyRule()
                    }
                }
                expected = ClusterRole(
                    metadata = Common.OBJECT_META,
                    aggregationRule = AggregationRule(
                        clusterRoleSelectors = listOf(Common.LABEL_SELECTOR)
                    ),
                    rules = listOf(policyRule)
                )
            }
        }
    }
}