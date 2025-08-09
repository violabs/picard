package io.violabs.picard.v2.resources.workload.set.stateful


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetStatusTest : FailureBuildSim<StatefulSetStatus, StatefulSetStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSetStatus, StatefulSetStatusDslBuilder> {
            requireScenario("replicas") {
                given(StatefulSetStatusDslBuilder())
            }
        }
    }
}