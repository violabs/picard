package io.violabs.picard.v2.resources.authentication.service.account

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceAccountListTest : FullBuildSim<ServiceAccountList, ServiceAccountList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceAccountList, ServiceAccountList.Builder> {
            scenario {
                id = "minimum"
                given(ServiceAccountList.Builder()) {
                    items {
                        account { }
                    }
                }
                expected = ServiceAccountList(
                    items = listOf(ServiceAccount())
                )
            }

            scenario {
                id = "full base level"
                given(ServiceAccountList.Builder()) {
                    items {
                        account { }
                    }

                    sharedMetadata()
                }
                expected = ServiceAccountList(
                    items = listOf(ServiceAccount()),
                    metadata = LIST_METADATA
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceAccountList, ServiceAccountList.Builder> {
            requireNotEmptyScenario("items") {
                given(ServiceAccountList.Builder())
            }
        }
    }
}