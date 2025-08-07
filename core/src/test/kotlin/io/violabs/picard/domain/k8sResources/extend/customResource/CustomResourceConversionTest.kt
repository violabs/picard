package io.violabs.picard.domain.k8sResources.extend.customResource


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CustomResourceConversionTest : FailureBuildSim<CustomResourceConversion, CustomResourceConversionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CustomResourceConversionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CustomResourceConversion, CustomResourceConversionDslBuilder> {
            requireScenario("strategy") {
                given(CustomResourceConversionDslBuilder())
            }
        }
    }
}