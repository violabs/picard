package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResizePolicyTest : FailureBuildSim<ResizePolicy, ResizePolicy.Builder>() {
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

private val FAILURE_POSSIBILITIES = possibilities<ResizePolicy, ResizePolicy.Builder> {
    scenario {
        id = "missing resourceName"
        given(ResizePolicy.Builder())
        exceptionMessage = withTemplate("resourceName")
    }

    scenario {
        id = "missing restartPolicy"
        given(ResizePolicy.Builder()) {
            resourceName = "cool-resource"
        }
        exceptionMessage = withTemplate("restartPolicy")
    }
}