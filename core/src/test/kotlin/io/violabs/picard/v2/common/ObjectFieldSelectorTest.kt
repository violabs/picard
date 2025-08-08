package io.violabs.picard.v2.common


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ObjectFieldSelectorTest : FailureBuildSim<ObjectFieldSelector, ObjectFieldSelectorDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ObjectFieldSelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ObjectFieldSelector, ObjectFieldSelectorDslBuilder> {
            requireScenario("fieldPath") {
                given(ObjectFieldSelectorDslBuilder())
            }
        }
    }
}