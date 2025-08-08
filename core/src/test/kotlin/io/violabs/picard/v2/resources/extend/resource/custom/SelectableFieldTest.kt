package io.violabs.picard.v2.resources.extend.resource.custom

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