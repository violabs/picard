package io.violabs.picard.v2.resources.storage.persistent.volume

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PersistentVolumeListTest : FullBuildSim<PersistentVolumeList, PersistentVolumeListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PersistentVolumeListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PersistentVolumeList, PersistentVolumeListDslBuilder> {
            scenario {
                id = "minimum"
                given(PersistentVolumeListDslBuilder()) {
                    items {
                        persistentVolume { }
                    }
                }
                expected = PersistentVolumeList(
                    items = listOf(PersistentVolume())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PersistentVolumeList, PersistentVolumeListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(PersistentVolumeListDslBuilder())
            }
        }
    }
}