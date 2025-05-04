package io.violabs.picard.domain.k8sResources.extend.deviceClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceClassListTest : FullBuildSim<DeviceClassList, DeviceClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceClassList, DeviceClassList.Builder> {
            scenario {
                id = "minimum"
                given(DeviceClassList.Builder()) {
                    items {
                        deviceClassItem {
                            spec {}
                        }
                    }
                }
                expected = DeviceClassList(
                    items = listOf(
                        DeviceClass(
                            spec = DeviceClass.Spec()
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeviceClassList, DeviceClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(DeviceClassList.Builder())
            }
        }
    }
}