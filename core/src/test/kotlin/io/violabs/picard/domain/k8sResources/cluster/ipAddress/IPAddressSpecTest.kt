package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressSpecTest : FullBuildSim<IPAddressSpec, IPAddressSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IPAddressSpec, IPAddressSpecDslBuilder> {
            scenario {
                id = "minimum"
                given(IPAddressSpecDslBuilder()) {
                    parentRef {
                        name = PLACEHOLDER
                        resource = PLACEHOLDER
                        group = PLACEHOLDER
                        namespace = PLACEHOLDER
                    }
                }
                expected = IPAddressSpec(
                    parentRef = ParentReference(
                        name = PLACEHOLDER,
                        resource = PLACEHOLDER,
                        group = PLACEHOLDER,
                        namespace = PLACEHOLDER
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IPAddressSpec, IPAddressSpecDslBuilder> {
            requireScenario("parentRef") {
                given(IPAddressSpecDslBuilder())
            }
        }
    }
}