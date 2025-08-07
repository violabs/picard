package io.violabs.picard.v2.resources.cluster.lease

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseTest : SuccessBuildSim<Lease, LeaseV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Lease, LeaseV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseV2DslBuilder())
                expected = Lease()
            }

            scenario {
                id = "full"
                given(LeaseV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
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
                    metadata = OBJECT_META,
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