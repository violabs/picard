package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCidrTest : SuccessBuildSim<ServiceCidrV2, ServiceCidrV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCidrTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCidrV2, ServiceCidrV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceCidrV2DslBuilder())
                expected = ServiceCidrV2()
            }

            scenario {
                id = "full"
                given(ServiceCidrV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        cidrs(PLACEHOLDER)
                    }
                    status {
                        conditions {
                            condition {
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                status = "True"
                                type = PLACEHOLDER
                                observedGeneration = 1
                            }
                        }
                    }
                }
                expected = ServiceCidrV2(
                    metadata = Common.OBJECT_META,
                    spec = ServiceCidrSpec(
                        cidrs = listOf(PLACEHOLDER)
                    ),
                    status = ServiceCidrStatus(
                        conditions = listOf(
                            Condition(
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER,
                                status = "True",
                                type = PLACEHOLDER,
                                observedGeneration = 1
                            )
                        )
                    )
                )
            }
        }
    }
}