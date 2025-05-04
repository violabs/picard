package io.violabs.picard.domain.k8sResources.workload.podTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodTemplateListTest : FullBuildSim<PodTemplateList, PodTemplateList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTemplateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodTemplateList, PodTemplateList.Builder> {
            scenario {
                id = "minimum"
                given(PodTemplateList.Builder()) {
                    items {
                        podTemplateItem {}
                    }
                }
                expected = PodTemplateList(
                    items = listOf(PodTemplate())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PodTemplateList, PodTemplateList.Builder> {
            requireNotEmptyScenario("items") {
                given(PodTemplateList.Builder())
            }
        }
    }
}