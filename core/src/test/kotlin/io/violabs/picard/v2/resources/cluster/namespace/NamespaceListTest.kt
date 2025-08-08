package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NamespaceListTest : FullBuildSim<NamespaceList, NamespaceListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NamespaceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<NamespaceList, NamespaceListDslBuilder> {
            scenario {
                id = "minimum"
                given(NamespaceListDslBuilder()) {
                    items {
                        namespace { }
                    }
                }
                expected = NamespaceList(
                    items = listOf(
                        Namespace()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<NamespaceList, NamespaceListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(NamespaceListDslBuilder())
            }
        }
    }
}