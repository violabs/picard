package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateTest : FullBuildSim<ResourceClaimTemplate, ResourceClaimTemplate.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaimTemplate, ResourceClaimTemplate.Builder> {
            scenario {
                id = "minimum"
                given(ResourceClaimTemplate.Builder()) {
                    spec {
                        resourceClaimSpec {}
                    }
                }
                expected = ResourceClaimTemplate(
                    spec = ResourceClaimTemplate.Spec(
                        spec = ResourceClaim.Spec()
                    )
                )
            }

            scenario {
                id = "full level 1"
                given(ResourceClaimTemplate.Builder()) {
                    sharedMetadata()
                    spec {
                        resourceClaimSpec {}
                        metadata {

                        }
                    }
                }
                expected = ResourceClaimTemplate(
                    metadata = METADATA,
                    spec = ResourceClaimTemplate.Spec(
                        spec = ResourceClaim.Spec(),
                        metadata = ObjectMetadata()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceClaimTemplate, ResourceClaimTemplate.Builder> {
            requireScenario("spec") {
                given(ResourceClaimTemplate.Builder())
            }
        }
    }
}