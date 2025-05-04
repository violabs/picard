package io.violabs.picard.domain.k8sResources.workload.replicaSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetSpecTest : FailureBuildSim<ReplicaSet.Spec, ReplicaSet.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ReplicaSet.Spec, ReplicaSet.Spec.Builder> {
            requireScenario("selector") {
                given(ReplicaSet.Spec.Builder())
            }
        }
    }
}