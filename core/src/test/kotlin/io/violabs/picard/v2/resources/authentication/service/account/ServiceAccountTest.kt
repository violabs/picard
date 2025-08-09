package io.violabs.picard.v2.resources.authentication.service.account


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectReference
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.common.LocalObjectReference
import org.junit.jupiter.api.BeforeAll

class ServiceAccountTest : SuccessBuildSim<ServiceAccount, ServiceAccountDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceAccount, ServiceAccountDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceAccountDslBuilder())
                expected = ServiceAccount()
            }

            scenario {
                id = "full"
                given(ServiceAccountDslBuilder()) {
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
                expected = ServiceAccount(
                    automountServiceAccountToken = true,
                    imagePullSecrets = listOf(LocalObjectReference(name = PLACEHOLDER)),
                    secrets = listOf(Common.OBJECT_REFERENCE)
                )
            }
        }
    }
}