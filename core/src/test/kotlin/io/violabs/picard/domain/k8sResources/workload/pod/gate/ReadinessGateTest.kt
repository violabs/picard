package io.violabs.picard.domain.k8sResources.workload.pod.gate


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReadinessGateTest : FailureBuildSim<ReadinessGate, ReadinessGateDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReadinessGateTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<ReadinessGate, ReadinessGateDslBuilder> {
    requireScenario("conditionType") {
        given(ReadinessGateDslBuilder())
    }
}