package io.violabs.picard.domain.k8sResources.workload.statefulSet


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetSpecTest : FailureBuildSim<StatefulSet.Spec, StatefulSet.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSet.Spec, StatefulSet.Spec.Builder> {
            requireScenario("serviceName") {
                given(StatefulSet.Spec.Builder())
            }

            requireScenario("selector") {
                given(StatefulSet.Spec.Builder()) {
                    serviceName = PLACEHOLDER
                }
            }
        }
    }
}