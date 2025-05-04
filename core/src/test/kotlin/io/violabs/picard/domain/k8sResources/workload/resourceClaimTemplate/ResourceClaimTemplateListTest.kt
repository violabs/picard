package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateListTest : FullBuildSim<ResourceClaimTemplateList, ResourceClaimTemplateList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ResourceClaimTemplateList, ResourceClaimTemplateList.Builder> {
                scenario {
                    id = "minimum"
                    given(ResourceClaimTemplateList.Builder()) {
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
                            spec = ResourceClaimTemplate.Spec(
                                spec = ResourceClaim.Spec()
                            )
                        ))
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ResourceClaimTemplateList, ResourceClaimTemplateList.Builder> {
                requireNotEmptyScenario("items") {
                    given(ResourceClaimTemplateList.Builder())
                }
            }
    }
}