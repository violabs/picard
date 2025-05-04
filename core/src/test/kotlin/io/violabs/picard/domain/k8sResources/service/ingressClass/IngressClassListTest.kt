package io.violabs.picard.domain.k8sResources.service.ingressClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassListTest : FullBuildSim<IngressClassList, IngressClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressClassList, IngressClassList.Builder> {
            scenario {
                id = "minimum"
                given(IngressClassList.Builder()) {
                    items {
                        ingressClassItem {  }
                    }
                }
                expected = IngressClassList(
                    items = listOf(IngressClass())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IngressClassList, IngressClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(IngressClassList.Builder())
            }
        }
    }
}