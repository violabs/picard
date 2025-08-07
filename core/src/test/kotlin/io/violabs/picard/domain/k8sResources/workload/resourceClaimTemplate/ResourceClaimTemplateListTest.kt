package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateListTest : FullBuildSim<ResourceClaimTemplateList, ResourceClaimTemplateListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ResourceClaimTemplateList, ResourceClaimTemplateListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ResourceClaimTemplateListDslBuilder()) {
                        items {
                            resourceClaimTemplateItem {
                                spec {
                                    resourceClaimSpec { }
                                }
                            }
                        }
                    }
                    expected = ResourceClaimTemplateList(
                        items = listOf(ResourceClaimTemplate(
                            spec = ResourceClaimTemplateSpec(
                                spec = ResourceClaimSpec()
                            )
                        ))
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ResourceClaimTemplateList, ResourceClaimTemplateListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(ResourceClaimTemplateListDslBuilder())
                }
            }
    }
}