package io.violabs.picard.v2.resources.storage.volume.attachment


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class InlineVolumeSpecTest : FailureBuildSim<InlineVolumeSpec, InlineVolumeSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            InlineVolumeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<InlineVolumeSpec, InlineVolumeSpecDslBuilder> {
            requireScenario("attached") {
                given(InlineVolumeSpecDslBuilder())
            }
        }
    }
}