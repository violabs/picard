package io.violabs.picard.v2.resources.workload.resource.claim.template

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.resource.claim.ResourceClaimSpec
import org.junit.jupiter.api.BeforeAll

class ResourceClaimTemplateTest : SuccessBuildSim<ResourceClaimTemplate, ResourceClaimTemplateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimTemplateTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaimTemplate, ResourceClaimTemplateDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceClaimTemplateDslBuilder()) {
                    spec {
                        spec {
                            // Empty ResourceClaimSpec - DeviceClaim is optional
                        }
                    }
                }
                expected = ResourceClaimTemplate(
                    spec = ResourceClaimTemplateSpec(
                        spec = ResourceClaimSpec()
                    )
                )
            }

            scenario {
                id = "full"
                given(ResourceClaimTemplateDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        spec {
                            // Empty ResourceClaimSpec - DeviceClaim is optional
                        }
                        metadata {
                            sharedObjectMeta()
                        }
                    }
                }
                expected = ResourceClaimTemplate(
                    metadata = OBJECT_META,
                    spec = ResourceClaimTemplateSpec(
                        spec = ResourceClaimSpec(),
                        metadata = OBJECT_META
                    )
                )
            }
        }
    }
}