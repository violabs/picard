package io.violabs.picard.v2.resources.cluster.service.api

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ApiServiceTest : SuccessBuildSim<ApiService, ApiServiceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ApiServiceTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ApiService, ApiServiceDslBuilder> {
            scenario {
                id = "minimum"
                given(ApiServiceDslBuilder())
                expected = ApiService()
            }

            scenario {
                id = "full"
                given(ApiServiceDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        groupPriorityMinimum = 1
                        versionPriority = 1
                        caBundle(1.toByte())
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
                        conditions {
                            apiServiceCondition {
                                status = PLACEHOLDER
                                type = PLACEHOLDER
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }
                    }
                }
                expected = ApiService(
                    metadata = Common.OBJECT_META,
                    spec = ApiServiceSpec(
                        groupPriorityMinimum = 1,
                        versionPriority = 1,
                        caBundle = listOf(1.toByte()),
                        group = PLACEHOLDER,
                        insecureSkipTLSVerify = true,
                        service = ServiceReference(
                            name = PLACEHOLDER,
                            namespace = PLACEHOLDER,
                            port = PORT_NUMBER
                        ),
                        version = PLACEHOLDER
                    ),
                    status = ApiServiceStatus(
                        conditions = listOf(
                            ApiServiceCondition(
                                status = PLACEHOLDER,
                                type = PLACEHOLDER,
                                lastTransitionTime = NOW,
                                message = PLACEHOLDER,
                                reason = PLACEHOLDER
                            )
                        )
                    )
                )
            }
        }
    }
}