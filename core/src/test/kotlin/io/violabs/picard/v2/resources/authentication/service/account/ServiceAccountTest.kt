package io.violabs.picard.v2.resources.authentication.service.account


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectReference
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.LocalObjectReference
import org.junit.jupiter.api.BeforeAll

class ServiceAccountTest : SuccessBuildSim<ServiceAccountV2, ServiceAccountV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceAccountV2, ServiceAccountV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceAccountV2DslBuilder())
                expected = ServiceAccountV2()
            }

            scenario {
                id = "full"
                given(ServiceAccountV2DslBuilder()) {
                    automountServiceAccountToken()
                    imagePullSecrets {
                        localObjectReference {
                            name = PLACEHOLDER
                        }
                    }

                    secrets {
                        objectReference {
                            sharedObjectReference()
                        }
                    }
                }
                expected = ServiceAccountV2(
                    automountServiceAccountToken = true,
                    imagePullSecrets = listOf(LocalObjectReference(name = PLACEHOLDER)),
                    secrets = listOf(Common.OBJECT_REFERENCE)
                )
            }
        }
    }
}