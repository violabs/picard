package io.violabs.picard.domain.k8sResources.workload.replicaSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetSpecTest : FailureBuildSim<ReplicaSetSpec, ReplicaSetSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ReplicaSetSpec, ReplicaSetSpecDslBuilder> {
            requireScenario("selector") {
                given(ReplicaSetSpecDslBuilder())
            }
        }
    }
}