package io.violabs.picard.domain.k8sResources.workload.statefulSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetStatusTest : FailureBuildSim<StatefulSet.Status, StatefulSet.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSet.Status, StatefulSet.Status.Builder> {
            requireScenario("replicas") {
                given(StatefulSet.Status.Builder())
            }
        }
    }
}