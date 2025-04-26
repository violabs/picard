package io.violabs.picard.domain.k8sResources.cluster.namespace


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NamespaceTest : SuccessBuildSim<Namespace, Namespace.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NamespaceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Namespace, Namespace.Builder> {
            scenario {
                id = "minimum"
                given(Namespace.Builder())
                expected = Namespace()
            }

            scenario {
                id = "full"
                given(Namespace.Builder()) {
                    sharedMetadata()
                    spec {
                        finalizers(PLACEHOLDER)
                    }

                    status {
                        conditions {
                            sharedCondition()
                            phase = PLACEHOLDER
                        }
                    }
                }
                expected = Namespace(
                    metadata = METADATA,
                    spec = Namespace.Spec(
                        finalizers = listOf(PLACEHOLDER)
                    ),
                    status = Namespace.Status(
                        conditions = listOf(CONDITION),
                        phase = PLACEHOLDER
                    )
                )
            }
        }
    }
}