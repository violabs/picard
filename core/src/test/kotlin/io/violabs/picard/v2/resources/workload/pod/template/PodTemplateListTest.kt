package io.violabs.picard.v2.resources.workload.pod.template


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
                        podTemplate {}
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