package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.ServiceCondition
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCIDRTest : SuccessBuildSim<ServiceCIDR, ServiceCIDR.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCIDRTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCIDR, ServiceCIDR.Builder> {
            scenario {
                id = "minimum"
                given(ServiceCIDR.Builder())
                expected = ServiceCIDR()
            }

            scenario {
                id = "full"
                given(ServiceCIDR.Builder()) {
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
                    spec = ServiceCIDR.Spec(
                        cidrs = listOf(PLACEHOLDER)
                    ),
                    status = ServiceCIDR.Status(
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