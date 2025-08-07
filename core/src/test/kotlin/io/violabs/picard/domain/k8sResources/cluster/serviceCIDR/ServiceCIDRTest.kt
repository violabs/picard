package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.condition.ServiceCondition
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCIDRTest : SuccessBuildSim<ServiceCIDR, ServiceCIDRDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCIDRTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCIDR, ServiceCIDRDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceCIDRDslBuilder())
                expected = ServiceCIDR()
            }

            scenario {
                id = "full"
                given(ServiceCIDRDslBuilder()) {
                    sharedMetadata()
                    spec {
                        cidrs(PLACEHOLDER)
                    }
                    this.status {
                        conditions {
                            condition {
                                status = BooleanType.True
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                                observedGeneration = 1
                            }
                        }
                    }
                }
                expected = ServiceCIDR(
                    metadata = METADATA,
                    spec = ServiceCIDRSpec(
                        cidrs = listOf(PLACEHOLDER)
                    ),
                    status = ServiceCIDRStatus(
                        conditions = listOf(
                            ServiceCondition(
                                status = BooleanType.True,
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER,
                                observedGeneration = 1
                            )
                        )
                    )
                )
            }
        }
    }
}