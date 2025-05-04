package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressListTest : FullBuildSim<IngressList, IngressList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressList, IngressList.Builder> {
            scenario {
                id = "minimum"
                given(IngressList.Builder()) {
                    items {
                        ingressItem {  }
                    }
                }
                expected = IngressList(
                    items = listOf(Ingress())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IngressList, IngressList.Builder> {
            requireNotEmptyScenario("items") {
                given(IngressList.Builder())
            }
        }
    }
}