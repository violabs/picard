package io.violabs.picard.v2.resources.workload.pod


//import io.violabs.picard.domain.k8sResources.storage.volume.Volume
import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Disabled

@Disabled
class PodSpecTest : FailureBuildSim<PodSpec, PodSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodSpec, PodSpecDslBuilder> {
//            requireNotEmptyScenario("containers") {
            requireScenario("containers") {
                given(PodSpecDslBuilder())
            }
        }
    }
}