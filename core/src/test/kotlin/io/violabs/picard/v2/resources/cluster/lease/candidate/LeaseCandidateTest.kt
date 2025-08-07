package io.violabs.picard.v2.resources.cluster.lease.candidate

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateTest : SuccessBuildSim<LeaseCandidate, LeaseCandidateV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidate, LeaseCandidateV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseCandidateV2DslBuilder()) {
                    spec {
                        binaryVersion = PLACEHOLDER
                        leaseName = PLACEHOLDER
                        strategy = PLACEHOLDER
                    }
                }
                expected = LeaseCandidate(
                    spec = LeaseCandidateSpec(
                        binaryVersion = PLACEHOLDER,
                        leaseName = PLACEHOLDER,
                        strategy = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                given(LeaseCandidateV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        binaryVersion = PLACEHOLDER
                        leaseName = PLACEHOLDER
                        strategy = PLACEHOLDER
                        emulationVersion = PLACEHOLDER
                        pingTime = TIMESTAMP
                        renewTime = TIMESTAMP
                    }
                }
                expected = LeaseCandidate(
                    metadata = OBJECT_META,
                    spec = LeaseCandidateSpec(
                        binaryVersion = PLACEHOLDER,
                        leaseName = PLACEHOLDER,
                        strategy = PLACEHOLDER,
                        emulationVersion = PLACEHOLDER,
                        pingTime = TIMESTAMP,
                        renewTime = TIMESTAMP
                    )
                )
            }
        }
    }
}