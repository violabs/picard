package io.violabs.picard.domain.k8sResources.config.secret


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SecretListTest : FullBuildSim<SecretList, SecretList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SecretList, SecretList.Builder> {
            scenario {
                id = "minimum"
                given(SecretList.Builder()) {
                    items {
                        secretItem {}
                    }
                }
                expected = SecretList(
                    items = listOf(Secret())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SecretList, SecretList.Builder> {
            requireNotEmptyScenario("items") {
                given(SecretList.Builder())
            }
        }
    }
}