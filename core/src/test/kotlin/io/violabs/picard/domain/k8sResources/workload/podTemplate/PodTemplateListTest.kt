package io.violabs.picard.domain.k8sResources.workload.podTemplate


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodTemplateListTest : FullBuildSim<PodTemplateList, PodTemplateListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTemplateListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodTemplateList, PodTemplateListDslBuilder> {
            scenario {
                id = "minimum"
                given(PodTemplateListDslBuilder()) {
                    items {
                        podTemplateItem {}
                    }
                }
                expected = PodTemplateList(
                    items = listOf(PodTemplate())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PodTemplateList, PodTemplateListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(PodTemplateListDslBuilder())
            }
        }
    }
}