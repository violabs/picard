package io.violabs.picard.v2.resources.service.ingressclass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassListTest : FullBuildSim<IngressClassList, IngressClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressClassList, IngressClassListDslBuilder> {
            scenario {
                id = "minimum"
                given(IngressClassListDslBuilder()) {
                    items {
                        ingressClass {  }
                    }
                }
                expected = IngressClassList(
                    items = listOf(IngressClass())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IngressClassList, IngressClassListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(IngressClassListDslBuilder())
            }
        }
    }
}