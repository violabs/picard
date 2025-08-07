package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HTTPGetActionTest : FullBuildSim<HTTPGetAction, HTTPGetActionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HTTPGetActionTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<HTTPGetAction, HTTPGetActionDslBuilder> {
    scenario {
        id = "minimum"
        given(HTTPGetActionDslBuilder()) {
            port("1234")

            httpHeaders {
                header("test", "test-value")
            }
        }
        expected = HTTPGetAction(
            port = IntOrString(str = "1234"),
            httpHeaders = listOf(
                HttpHeader("test", "test-value")
            )
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<HTTPGetAction, HTTPGetActionDslBuilder> {
    scenario {
        id = "missing port"
        given(HTTPGetActionDslBuilder())
        exceptionMessage = withTemplate("port")
    }
}