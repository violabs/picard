package io.violabs.picard.domain.k8sResources.cluster.lease


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseTest : SuccessBuildSim<Lease, Lease.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Lease, Lease.Builder> {
            scenario {
                id = "minimum"
                given(Lease.Builder())
                expected = Lease()
            }

            scenario {
                id = "full"
                given(Lease.Builder()) {
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
                    spec = Lease.Spec(
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