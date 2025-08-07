package io.violabs.picard.domain.k8sResources.workload.pod.action


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class GRPCActionTest : FailureBuildSim<GRPCAction, GRPCActionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            GRPCActionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<GRPCAction, GRPCActionDslBuilder> {
    scenario {
        id = "missing port"
        given(GRPCActionDslBuilder())
        exceptionMessage = withTemplate("port")
    }
}