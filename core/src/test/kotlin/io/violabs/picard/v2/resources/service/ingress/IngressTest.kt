package io.violabs.picard.v2.resources.service.ingress

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressTest : SuccessBuildSim<IngressV2, IngressV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IngressV2, IngressV2DslBuilder> {
            scenario {
                id = "minimum"
                given(IngressV2DslBuilder())
                expected = IngressV2()
            }

            scenario {
                id = "with_spec"
                given(IngressV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        ingressClassName = PLACEHOLDER
                    }
                }
                expected = IngressV2(
                    metadata = OBJECT_META,
                    spec = IngressSpec(
                        ingressClassName = PLACEHOLDER
                    )
                )
            }
        }
    }
}