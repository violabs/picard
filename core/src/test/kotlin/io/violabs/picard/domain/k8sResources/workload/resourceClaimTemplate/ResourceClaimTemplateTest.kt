package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateTest : FullBuildSim<ResourceClaimTemplate, ResourceClaimTemplateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaimTemplate, ResourceClaimTemplateDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceClaimTemplateDslBuilder()) {
                    spec {
                        resourceClaimSpec {}
                    }
                }
                expected = ResourceClaimTemplate(
                    spec = ResourceClaimTemplateSpec(
                        spec = ResourceClaimSpec()
                    )
                )
            }

            scenario {
                id = "full level 1"
                given(ResourceClaimTemplateDslBuilder()) {
                    sharedMetadata()
                    spec {
                        resourceClaimSpec {}
                        metadata {

                        }
                    }
                }
                expected = ResourceClaimTemplate(
                    metadata = METADATA,
                    spec = ResourceClaimTemplateSpec(
                        spec = ResourceClaimSpec(),
                        metadata = ObjectMetadata()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceClaimTemplate, ResourceClaimTemplateDslBuilder> {
            requireScenario("spec") {
                given(ResourceClaimTemplateDslBuilder())
            }
        }
    }
}