package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
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
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        finalizers(PLACEHOLDER)
                    }
                    status {
                        conditions {
                            namespaceCondition {
                                status = "True"
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }
                        phase = PLACEHOLDER
                    }
                }
                expected = Namespace(
                    metadata = OBJECT_META,
                    spec = NamespaceSpec(
                        finalizers = listOf(PLACEHOLDER)
                    ),
                    status = NamespaceStatus(
                        conditions = listOf(
                            NamespaceCondition(
                                status = "True",
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER
                            )
                        ),
                        phase = PLACEHOLDER
                    )
                )
            }
        }
    }
}