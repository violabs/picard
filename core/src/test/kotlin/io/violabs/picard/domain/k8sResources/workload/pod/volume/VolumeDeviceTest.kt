package io.violabs.picard.domain.k8sResources.workload.pod.volume


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeDeviceTest : FailureBuildSim<VolumeDevice, VolumeDevice.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeDeviceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<VolumeDevice, VolumeDevice.Builder> {
    scenario {
        id = "missing devicePath"
        given(VolumeDevice.Builder())
        exceptionMessage = withTemplate("devicePath")
    }

//    scenario {
//        id = "missing name"
//        given(VolumeDevice.Builder()) {
//            devicePath = "/test"
//        }
//        exceptionMessage = withTemplate("name")
//    }
}