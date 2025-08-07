package io.violabs.picard.v2.resources.authentication.service.account

import io.violabs.picard.Common.sharedListMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceAccountListTest : FullBuildSim<ServiceAccountList, ServiceAccountListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceAccountList, ServiceAccountListDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceAccountListDslBuilder()) {
                    items {
                        serviceAccount {  }
                    }
                }
                expected = ServiceAccountList(
                    items = listOf(ServiceAccount())
                )
            }

            scenario {
                id = "full base level"
                given(ServiceAccountListDslBuilder()) {
                    items {
                        serviceAccount { }
                    }

                    metadata { sharedListMeta() }
                }
                expected = ServiceAccountList(
                    items = listOf(ServiceAccount()),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceAccountList, ServiceAccountListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ServiceAccountListDslBuilder())
            }
        }
    }
}