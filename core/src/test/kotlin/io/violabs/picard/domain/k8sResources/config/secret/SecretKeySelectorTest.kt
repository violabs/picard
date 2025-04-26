package io.violabs.picard.domain.k8sResources.config.secret


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SecretKeySelectorTest : FullBuildSim<SecretKeySelector, SecretKeySelector.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretKeySelectorTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<SecretKeySelector, SecretKeySelector.Builder> {
            scenario {
                idForFalseBooleanValues()
                given(SecretKeySelector.Builder()) {
                    key = PLACEHOLDER
                    optional(false)
                }
                expected = SecretKeySelector(
                    key = PLACEHOLDER,
                    optional = false
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<SecretKeySelector, SecretKeySelector.Builder> {
            requireScenario("key") {
                given(SecretKeySelector.Builder())
            }
        }
    }
}