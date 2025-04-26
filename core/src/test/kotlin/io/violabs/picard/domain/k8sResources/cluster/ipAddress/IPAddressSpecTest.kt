package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressSpecTest : FullBuildSim<IPAddress.Spec, IPAddress.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IPAddress.Spec, IPAddress.Spec.Builder> {
            scenario {
                id = "minimum"
                given(IPAddress.Spec.Builder()) {
                    parentRef {
                        name = PLACEHOLDER
                        resource = PLACEHOLDER
                        group = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                }
                expected = IPAddress.Spec(
                    parentRef = ParentReference(
                        name = PLACEHOLDER,
                        resource = PLACEHOLDER,
                        group = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IPAddress.Spec, IPAddress.Spec.Builder> {
            requireScenario("parentRef") {
                given(IPAddress.Spec.Builder())
            }
        }
    }
}