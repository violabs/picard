package io.violabs.picard.domain.k8sResources.cluster.namespace


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NamespaceListTest : FullBuildSim<NamespaceList, NamespaceList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NamespaceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NamespaceList, NamespaceList.Builder> {
            scenario {
                id = "minimum"
                given(NamespaceList.Builder()) {
                    items {
                        name {  }
                    }
                }
                expected = NamespaceList(
                    items = listOf(
                        Namespace()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<NamespaceList, NamespaceList.Builder> {
            requireNotEmptyScenario("items") {
                given(NamespaceList.Builder())
            }
        }
    }
}