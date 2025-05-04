package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeClaimListTest : FullBuildSim<PersistentVolumeClaimList, PersistentVolumeClaimList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeClaimListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<PersistentVolumeClaimList, PersistentVolumeClaimList.Builder> {
                scenario {
                    id = "minimum"
                    given(PersistentVolumeClaimList.Builder()) {
                        items {
                            persistentVolumeClaimItem {  }
                        }
                    }
                    expected = PersistentVolumeClaimList(items = listOf(
                        PersistentVolumeClaim()
                    ))
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<PersistentVolumeClaimList, PersistentVolumeClaimList.Builder> {
                requireNotEmptyScenario("items") {
                    given(PersistentVolumeClaimList.Builder())
                }
            }
    }
}