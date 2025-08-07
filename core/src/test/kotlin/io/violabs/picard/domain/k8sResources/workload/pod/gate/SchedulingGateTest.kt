package io.violabs.picard.domain.k8sResources.workload.pod.gate


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SchedulingGateTest : FailureBuildSim<SchedulingGate, SchedulingGateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SchedulingGateTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<SchedulingGate, SchedulingGateDslBuilder> {
    requireScenario("name") {
        given(SchedulingGateDslBuilder())
    }
}