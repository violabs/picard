package io.violabs.picard.v2.resources.workload.resource.claim.template


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimSpec
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
                            resourceClaimTemplate {
                                spec {
                                    spec {

                                    }
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