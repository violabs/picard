package io.violabs.picard.domain.k8sResources.cluster.apiService


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class APIServiceListTest : FullBuildSim<APIServiceList, APIServiceList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            APIServiceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<APIServiceList, APIServiceList.Builder> {
            scenario {
                id = "minimum"
                given(APIServiceList.Builder()) {
                    items {
                        service {  }
                    }
                }
                expected = APIServiceList(
                    items = listOf(APIService())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<APIServiceList, APIServiceList.Builder> {
            requireNotEmptyScenario("items") {
                given(APIServiceList.Builder())
            }
        }
    }
}