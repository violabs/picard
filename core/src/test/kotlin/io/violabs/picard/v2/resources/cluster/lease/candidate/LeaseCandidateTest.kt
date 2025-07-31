package io.violabs.picard.v2.resources.cluster.lease.candidate

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.BuildSim.Companion.TIMESTAMP
import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LeaseCandidateTest : SuccessBuildSim<LeaseCandidateV2, LeaseCandidateV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LeaseCandidateTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<LeaseCandidateV2, LeaseCandidateV2DslBuilder> {
            scenario {
                id = "minimum"
                given(LeaseCandidateV2DslBuilder()) {
                    spec {
                        binaryVersion = PLACEHOLDER
                        leaseName = PLACEHOLDER
                        strategy = PLACEHOLDER
                    }
                }
                expected = LeaseCandidateV2(
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
                expected = LeaseCandidateV2(
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