package io.violabs.picard.v2.resources.config.secret

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SecretListTest : FullBuildSim<SecretList, SecretListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SecretList, SecretListDslBuilder> {
            scenario {
                id = "minimum"
                given(SecretListDslBuilder()) {
                    items {
                        secret {}
                    }
                }
                expected = SecretList(
                    items = listOf(Secret())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SecretList, SecretListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(SecretListDslBuilder())
            }
        }
    }
}