package io.violabs.picard.domain.k8sResources.workload.pod.action


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TCPSocketActionTest : FullBuildSim<TCPSocketAction, TCPSocketAction.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TCPSocketActionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<TCPSocketAction, TCPSocketAction.Builder> {
    scenario {
        id = "minimum"
        given(TCPSocketAction.Builder()) {
            port("8080")
        }
        expected = TCPSocketAction(
            port = IntOrString(str = "8080")
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<TCPSocketAction, TCPSocketAction.Builder> {
    scenario {
        id = "missing port"
        given(TCPSocketAction.Builder())
        exceptionMessage = withTemplate("port")
    }
}