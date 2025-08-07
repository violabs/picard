package io.violabs.picard.domain.k8sResources.workload.pod.affinity


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodAffinityTermTest : FailureBuildSim<PodAffinityTerm, PodAffinityTermDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodAffinityTermTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<PodAffinityTerm, PodAffinityTermDslBuilder> {
    requireScenario("topologyKey") {
        given(PodAffinityTermDslBuilder())
    }
}