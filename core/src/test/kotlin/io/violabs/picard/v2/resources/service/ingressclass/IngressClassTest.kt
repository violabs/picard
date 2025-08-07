package io.violabs.picard.v2.resources.service.ingressclass

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassTest : SuccessBuildSim<IngressClass, IngressClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressClass, IngressClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(IngressClassV2DslBuilder())
                expected = IngressClass()
            }

            scenario {
                id = "full"
                given(IngressClassV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        controller = PLACEHOLDER
                        parameters {
                            kind = PLACEHOLDER
                            name = PLACEHOLDER
                            apiGroup = PLACEHOLDER
                            namespace = PLACEHOLDER
                            scope = PLACEHOLDER
                        }
                    }
                }
                expected = IngressClass(
                    metadata = OBJECT_META,
                    spec = IngressClassSpec(
                        controller = PLACEHOLDER,
                        parameters = IngressClassParametersReference(
                            kind = PLACEHOLDER,
                            name = PLACEHOLDER,
                            apiGroup = PLACEHOLDER,
                            namespace = PLACEHOLDER,
                            scope = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}