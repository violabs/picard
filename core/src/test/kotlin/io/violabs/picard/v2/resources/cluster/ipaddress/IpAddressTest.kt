package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IpAddressTest : SuccessBuildSim<IpAddress, IpAddressDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IpAddressTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IpAddress, IpAddressDslBuilder> {
            scenario {
                id = "minimum"
                given(IpAddressDslBuilder())
                expected = IpAddress()
            }

            scenario {
                id = "full"
                given(IpAddressDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        parentRef {
                            name = PLACEHOLDER
                            resource = PLACEHOLDER
                            group = PLACEHOLDER
                            namespace = PLACEHOLDER
                        }
                    }
                }
                expected = IpAddress(
                    metadata = OBJECT_META,
                    spec = IPAddressSpec(
                        parentRef = ParentReference(
                            name = PLACEHOLDER,
                            resource = PLACEHOLDER,
                            group = PLACEHOLDER,
                            namespace = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}