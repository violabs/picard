package io.violabs.picard.domain.k8sResources.policy.limitRange


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeItemTest : FailureBuildSim<LimitRangeItem, LimitRangeItem.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeItemTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LimitRangeItem, LimitRangeItem.Builder> {
            requireScenario("type") {
                given(LimitRangeItem.Builder())
            }
        }
    }
}