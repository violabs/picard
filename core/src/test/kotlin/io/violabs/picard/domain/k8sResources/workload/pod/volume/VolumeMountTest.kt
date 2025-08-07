package io.violabs.picard.domain.k8sResources.workload.pod.volume

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeMountTest : FullBuildSim<VolumeMount, VolumeMountDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeMountTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<VolumeMount, VolumeMountDslBuilder> {
    scenario {
        idForFalseBooleanValues()
        given(VolumeMountDslBuilder()) {
            name = "cool-volume"
            mountPath = "/cool-path"
            readOnly = false
        }
        expected = VolumeMount(
            "cool-volume",
            "/cool-path",
            readOnly = false
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<VolumeMount, VolumeMountDslBuilder> {
    requireScenario("name") {
        given(VolumeMountDslBuilder())
    }

    requireScenario("mountPath") {
        given(VolumeMountDslBuilder()) {
            name = "cool-volume"
        }
    }
}