package io.violabs.picard.v2.resources.cluster.service.api


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ApiServiceListTest : FullBuildSim<ApiServiceList, ApiServiceListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ApiServiceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ApiServiceList, ApiServiceListDslBuilder> {
            scenario {
                id = "minimum"
                given(ApiServiceListDslBuilder()) {
                    items {
                        apiService {  }
                    }
                }
                expected = ApiServiceList(
                    items = listOf(ApiService())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ApiServiceList, ApiServiceListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ApiServiceListDslBuilder())
            }
        }
    }
}