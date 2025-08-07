package io.violabs.picard.domain.k8sResources.workload.pod.affinity


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class WeightedPodAffinityTermTest : FailureBuildSim<WeightedPodAffinityTerm, WeightedPodAffinityTermDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            WeightedPodAffinityTermTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<WeightedPodAffinityTerm, WeightedPodAffinityTermDslBuilder> {
    requireScenario("podAffinityTerm") {
        given(WeightedPodAffinityTermDslBuilder())
    }

    requireScenario("weight") {
        given(WeightedPodAffinityTermDslBuilder()) {
            podAffinityTerm {
                topologyKey = "podAffinityTerm"
            }
        }
    }
}