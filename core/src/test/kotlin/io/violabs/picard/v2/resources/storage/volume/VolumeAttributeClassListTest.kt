package io.violabs.picard.v2.resources.storage.volume

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttributeClassListTest : FullBuildSim<VolumeAttributesClassList, VolumeAttributesClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttributeClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<VolumeAttributesClassList, VolumeAttributesClassListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(VolumeAttributesClassListDslBuilder()) {
                        items {
                            volumeAttributesClass {
                                driverName = PLACEHOLDER
                            }
                        }
                    }
                    expected = VolumeAttributesClassList(
                        items = listOf(VolumeAttributesClass(driverName = PLACEHOLDER))
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<VolumeAttributesClassList, VolumeAttributesClassListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(VolumeAttributesClassListDslBuilder())
                }
            }
    }
}