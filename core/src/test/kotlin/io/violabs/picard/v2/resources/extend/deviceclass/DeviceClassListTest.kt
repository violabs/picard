package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeviceClassListTest : FullBuildSim<DeviceClassList, DeviceClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeviceClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeviceClassList, DeviceClassListDslBuilder> {
            scenario {
                id = "minimum"
                given(DeviceClassListDslBuilder()) {
                    items {
                        deviceClass {
                            spec {}
                        }
                    }
                }
                expected = DeviceClassList(
                    items = listOf(
                        DeviceClass(
                            spec = DeviceClassSpec()
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeviceClassList, DeviceClassListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(DeviceClassListDslBuilder())
            }
        }
    }
}