package io.violabs.picard.v2.resources.cluster.ipaddress

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressTest : SuccessBuildSim<IpAddressV2, IPAddressV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IpAddressV2, IPAddressV2DslBuilder> {
            scenario {
                id = "minimum"
                given(IPAddressV2DslBuilder())
                expected = IpAddressV2()
            }

            scenario {
                id = "full"
                given(IPAddressV2DslBuilder()) {
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
                expected = IpAddressV2(
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