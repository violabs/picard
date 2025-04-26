package io.violabs.picard.domain.k8sResources.cluster.apiService


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class APIServiceTest : SuccessBuildSim<APIService, APIService.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            APIServiceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<APIService, APIService.Builder> {
            scenario {
                id = "minimum"
                given(APIService.Builder())
                expected = APIService()
            }

            scenario {
                id = "full"
                given(APIService.Builder()) {
                    sharedMetadata()
                    spec {
                        groupPriorityMinimum = 1
                        versionPriority = 1
                        caBundle(BYTE_1, BYTE_2)
                        group = PLACEHOLDER
                        insecureSkipTLSVerify()
                        service {
                            name = PLACEHOLDER
                            namespace = PLACEHOLDER
                            port = PORT_NUMBER
                        }
                        version = PLACEHOLDER
                    }
                    status {
                        conditions { sharedCondition() }
                    }
                }
                expected = APIService(
                    metadata = METADATA,
                    spec = APIService.Spec(
                        groupPriorityMinimum = 1,
                        versionPriority = 1,
                        caBundle = BYTES,
                        group = PLACEHOLDER,
                        insecureSkipTLSVerify = true,
                        service = ServiceReference(
                            name = PLACEHOLDER,
                            namespace = PLACEHOLDER,
                            port = PORT_NUMBER
                        ),
                        version = PLACEHOLDER
                    ),
                    status = APIService.Status(
                        conditions = listOf(CONDITION)
                    )
                )
            }
        }
    }
}