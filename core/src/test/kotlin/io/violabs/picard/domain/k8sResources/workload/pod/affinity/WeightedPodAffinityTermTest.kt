package io.violabs.picard.domain.k8sResources.workload.pod.affinity


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WeightedPodAffinityTermTest : FailureBuildSim<WeightedPodAffinityTerm, WeightedPodAffinityTerm.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            WeightedPodAffinityTermTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<WeightedPodAffinityTerm, WeightedPodAffinityTerm.Builder> {
    requireScenario("podAffinityTerm") {
        given(WeightedPodAffinityTerm.Builder())
    }

    requireScenario("weight") {
        given(WeightedPodAffinityTerm.Builder()) {
            podAffinityTerm {
                topologyKey = "podAffinityTerm"
            }
        }
    }
}