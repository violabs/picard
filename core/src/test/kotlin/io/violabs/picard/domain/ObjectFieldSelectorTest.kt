package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectFieldSelectorTest : FailureBuildSim<ObjectFieldSelector, ObjectFieldSelector.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectFieldSelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectFieldSelector, ObjectFieldSelector.Builder> {
            requireScenario("fieldPath") {
                given(ObjectFieldSelector.Builder())
            }
        }
    }
}