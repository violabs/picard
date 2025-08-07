package io.violabs.picard.domain.k8sResources.service.ingressClass


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassTest : SuccessBuildSim<IngressClass, IngressClassDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<IngressClass, IngressClassDslBuilder> {
            scenario {
                id = "minimum"
                given(IngressClassDslBuilder())
                expected = IngressClass()
            }

            scenario {
                id = "full"
                given(IngressClassDslBuilder()) {
                    sharedMetadata()
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
                    metadata = METADATA,
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