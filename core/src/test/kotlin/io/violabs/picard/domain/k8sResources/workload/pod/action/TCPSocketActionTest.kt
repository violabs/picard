package io.violabs.picard.domain.k8sResources.workload.pod.action


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TCPSocketActionTest : FullBuildSim<TCPSocketAction, TCPSocketActionDslBuilder>() {
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

private val SUCCESS_POSSIBILITIES = possibilities<TCPSocketAction, TCPSocketActionDslBuilder> {
    scenario {
        id = "minimum"
        given(TCPSocketActionDslBuilder()) {
            port("8080")
        }
        expected = TCPSocketAction(
            port = IntOrString(str = "8080")
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<TCPSocketAction, TCPSocketActionDslBuilder> {
    scenario {
        id = "missing port"
        given(TCPSocketActionDslBuilder())
        exceptionMessage = withTemplate("port")
    }
}