package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressTest : SuccessBuildSim<IPAddress, IPAddress.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<IPAddress, IPAddress.Builder> {
            scenario {
                id = "minimum"
                given(IPAddress.Builder()) {

                }
                expected = IPAddress()
            }
        }
    }
}