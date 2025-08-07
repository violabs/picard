package io.violabs.picard.domain.k8sResources.cluster.namespace


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NamespaceTest : SuccessBuildSim<Namespace, NamespaceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NamespaceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Namespace, NamespaceDslBuilder> {
            scenario {
                id = "minimum"
                given(NamespaceDslBuilder())
                expected = Namespace()
            }

            scenario {
                id = "full"
                given(NamespaceDslBuilder()) {
                    sharedMetadata()
                    spec {
                        finalizers(PLACEHOLDER)
                    }

                    this.status {
                        conditions {
                            sharedCondition()
                            phase = PLACEHOLDER
                        }
                    }
                }
                expected = Namespace(
                    metadata = METADATA,
                    spec = NamespaceSpec(
                        finalizers = listOf(PLACEHOLDER)
                    ),
                    status = NamespaceStatus(
                        conditions = listOf(CONDITION),
                        phase = PLACEHOLDER
                    )
                )
            }
        }
    }
}