package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AttachedVolumeTest : FailureBuildSim<AttachedVolume, AttachedVolumeDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AttachedVolumeTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AttachedVolume, AttachedVolumeDslBuilder> {
            requireScenario("devicePath") {
                given(AttachedVolumeDslBuilder())
            }

            requireScenario("name") {
                given(AttachedVolumeDslBuilder()) {
                    devicePath = PLACEHOLDER
                }
            }
        }
    }
}