package io.violabs.picard.domain.k8sResources.storage.persistentVolume.claim


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeClaimListTest : FullBuildSim<PersistentVolumeClaimList, PersistentVolumeClaimListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeClaimListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<PersistentVolumeClaimList, PersistentVolumeClaimListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(PersistentVolumeClaimListDslBuilder()) {
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
            possibilities<PersistentVolumeClaimList, PersistentVolumeClaimListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(PersistentVolumeClaimListDslBuilder())
                }
            }
    }
}