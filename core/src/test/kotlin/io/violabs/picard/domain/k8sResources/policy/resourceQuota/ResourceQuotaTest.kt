package io.violabs.picard.domain.k8sResources.policy.resourceQuota


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.Operator
import io.violabs.picard.domain.k8sResources.policy.ScopeSelector
import io.violabs.picard.domain.k8sResources.policy.ScopedResourceSelectorRequirement
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceQuotaTest : SuccessBuildSim<ResourceQuota, ResourceQuota.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceQuotaTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ResourceQuota, ResourceQuota.Builder> {
            scenario {
                id = "minimum"
                given(ResourceQuota.Builder()) {

                }
                expected = ResourceQuota()
            }

            scenario {
                id = "full"
                given(ResourceQuota.Builder()) {
                    sharedMetadata()
                    spec {
                        hard(QUANTITY_PAIR)
                        scopeSelector {
                            matchExpressions {
                                addScopedResourceSelectorRequirement {
                                    operator = Operator.In
                                    scopeName = PLACEHOLDER
                                    values(PLACEHOLDER)
                                }
                            }
                        }
                        scopes(PLACEHOLDER)
                    }
                    this.status {
                        hard(QUANTITY_PAIR)
                        used(QUANTITY_PAIR)
                    }
                }
                expected = ResourceQuota(
                    metadata = METADATA,
                    spec = ResourceQuota.Spec(
                        hard = QUANTITY_MAP,
                        scopeSelector = ScopeSelector(
                            matchExpressions = listOf(
                                ScopedResourceSelectorRequirement(
                                    operator = Operator.In,
                                    scopeName = PLACEHOLDER,
                                    values = PLACEHOLDER_LIST
                                )
                            )
                        ),
                        scopes = PLACEHOLDER_LIST
                    ),
                    status = ResourceQuota.Status(
                        hard = QUANTITY_MAP,
                        used = QUANTITY_MAP
                    )
                )
            }
        }
    }
}