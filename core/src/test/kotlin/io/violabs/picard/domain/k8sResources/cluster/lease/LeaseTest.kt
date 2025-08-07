package io.violabs.picard.domain.k8sResources.cluster.lease


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseTest : SuccessBuildSim<Lease, LeaseDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Lease, LeaseDslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseDslBuilder())
                expected = Lease()
            }

            scenario {
                id = "full"
                given(LeaseDslBuilder()) {
                    sharedMetadata()
                    spec {
                        acquireTime = TIMESTAMP
                        holderIdentity = PLACEHOLDER
                        leaseDurationSeconds = 1
                        leaseTransitions = 1
                        preferredHolder = PLACEHOLDER
                        renewTime = TIMESTAMP
                        strategy = PLACEHOLDER
                    }
                }
                expected = Lease(
                    metadata = METADATA,
                    spec = LeaseSpec(
                        acquireTime = TIMESTAMP,
                        holderIdentity = PLACEHOLDER,
                        leaseDurationSeconds = 1,
                        leaseTransitions = 1,
                        preferredHolder = PLACEHOLDER,
                        renewTime = TIMESTAMP,
                        strategy = PLACEHOLDER
                    )
                )
            }
        }
    }
}