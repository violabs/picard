package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HTTPGetActionTest : FullBuildSim<HTTPGetAction, HTTPGetAction.Builder>() {
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

private val SUCCESS_POSSIBILITIES = possibilities<HTTPGetAction, HTTPGetAction.Builder> {
    scenario {
        id = "minimum"
        given(HTTPGetAction.Builder()) {
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

private val FAILURE_POSSIBILITIES = possibilities<HTTPGetAction, HTTPGetAction.Builder> {
    scenario {
        id = "missing port"
        given(HTTPGetAction.Builder())
        exceptionMessage = withTemplate("port")
    }
}