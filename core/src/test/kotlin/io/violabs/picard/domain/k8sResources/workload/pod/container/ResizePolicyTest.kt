package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResizePolicyTest : FailureBuildSim<ResizePolicy, ResizePolicyDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            buildSetup(
                ResizePolicyTest::class,
                failureScenariosSet = FAILURE_POSSIBILITIES
            )
        }
    }
}

private val FAILURE_POSSIBILITIES = possibilities<ResizePolicy, ResizePolicyDslBuilder> {
    scenario {
        id = "missing resourceName"
        given(ResizePolicyDslBuilder())
        exceptionMessage = withTemplate("resourceName")
    }

    scenario {
        id = "missing restartPolicy"
        given(ResizePolicyDslBuilder()) {
            resourceName = "cool-resource"
        }
        exceptionMessage = withTemplate("restartPolicy")
    }
}