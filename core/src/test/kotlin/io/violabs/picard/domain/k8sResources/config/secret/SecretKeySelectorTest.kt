package io.violabs.picard.domain.k8sResources.config.secret


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SecretKeySelectorTest : FailureBuildSim<SecretKeySelector, SecretKeySelectorDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretKeySelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SecretKeySelector, SecretKeySelectorDslBuilder> {
            requireScenario("key") {
                given(SecretKeySelectorDslBuilder())
            }
        }
    }
}