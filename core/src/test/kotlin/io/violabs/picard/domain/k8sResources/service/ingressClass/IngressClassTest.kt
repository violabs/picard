package io.violabs.picard.domain.k8sResources.service.ingressClass


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IngressClassTest : SuccessBuildSim<IngressClass, IngressClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IngressClassTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<IngressClass, IngressClass.Builder> {
            scenario {
                id = "minimum"
                given(IngressClass.Builder())
                expected = IngressClass()
            }

            scenario {
                id = "full"
                given(IngressClass.Builder()) {
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
                    spec = IngressClass.Spec(
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