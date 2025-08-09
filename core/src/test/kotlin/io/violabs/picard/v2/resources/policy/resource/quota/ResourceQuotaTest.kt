package io.violabs.picard.v2.resources.policy.resource.quota


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceQuotaTest : SuccessBuildSim<ResourceQuota, ResourceQuotaDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceQuotaTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceQuota, ResourceQuotaDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceQuotaDslBuilder())
                expected = ResourceQuota()
            }

            scenario {
                id = "full"
                given(ResourceQuotaDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        hard(QUANTITY_PAIR)
                        scopeSelector {
                            matchExpressions {
                                scopedResourceSelectorRequirement {
                                    operator = ScopedResourceSelectorRequirement.Operator.In
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
                    metadata = Common.OBJECT_META,
                    spec = ResourceQuotaSpec(
                        hard = QUANTITY_MAP,
                        scopeSelector = ScopeSelector(
                            matchExpressions = listOf(
                                ScopedResourceSelectorRequirement(
                                    operator = ScopedResourceSelectorRequirement.Operator.In,
                                    scopeName = PLACEHOLDER,
                                    values = PLACEHOLDER_LIST
                                )
                            )
                        ),
                        scopes = PLACEHOLDER_LIST
                    ),
                    status = ResourceQuotaStatus(
                        hard = QUANTITY_MAP,
                        used = QUANTITY_MAP
                    )
                )
            }
        }
    }
}