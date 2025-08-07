package io.violabs.picard.domain.k8sResources.extend.json


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SelectableFieldTest : FailureBuildSim<SelectableField, SelectableFieldDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SelectableFieldTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SelectableField, SelectableFieldDslBuilder> {
            requireScenario("jsonPath") {
                given(SelectableFieldDslBuilder())
            }
        }
    }
}