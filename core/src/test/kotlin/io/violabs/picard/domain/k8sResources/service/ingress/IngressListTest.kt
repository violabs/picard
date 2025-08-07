package io.violabs.picard.domain.k8sResources.service.ingress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressListTest : FullBuildSim<IngressList, IngressListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressList, IngressListDslBuilder> {
            scenario {
                id = "minimum"
                given(IngressListDslBuilder()) {
                    items {
                        ingressItem {  }
                    }
                }
                expected = IngressList(
                    items = listOf(Ingress())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IngressList, IngressListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(IngressListDslBuilder())
            }
        }
    }
}