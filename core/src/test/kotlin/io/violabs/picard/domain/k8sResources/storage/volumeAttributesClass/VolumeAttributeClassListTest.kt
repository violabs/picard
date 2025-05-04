package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttributeClassListTest : FullBuildSim<VolumeAttributesClassList, VolumeAttributesClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttributeClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttributesClassList, VolumeAttributesClassList.Builder> {
            scenario {
                id = "minimum"
                given(VolumeAttributesClassList.Builder()) {
                    items {
                        volumeAttributesClassItem {  }
                    }
                }
                expected = VolumeAttributesClassList(
                    items = listOf(VolumeAttributesClass())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<VolumeAttributesClassList, VolumeAttributesClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(VolumeAttributesClassList.Builder())
            }
        }
    }
}