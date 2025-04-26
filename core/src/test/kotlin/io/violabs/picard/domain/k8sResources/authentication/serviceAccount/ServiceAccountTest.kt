package io.violabs.picard.domain.k8sResources.authentication.serviceAccount


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.LocalObjectReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceAccountTest : SuccessBuildSim<ServiceAccount, ServiceAccount.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<ServiceAccount, ServiceAccount.Builder> {
            scenario {
                id = "minimum"
                given(ServiceAccount.Builder()) {
                    automountServiceAccountToken()
                    imagePullSecrets {
                        reference(PLACEHOLDER)
                    }

                    secrets {
                        reference { sharedObjectReference() }
                    }
                }
                expected = ServiceAccount(
                    automountServiceAccountToken = true,
                    imagePullSecrets = listOf(
                        LocalObjectReference(name = PLACEHOLDER)
                    ),
                    secrets = listOf(OBJECT_REFERENCE)
                )
            }
        }
    }
}