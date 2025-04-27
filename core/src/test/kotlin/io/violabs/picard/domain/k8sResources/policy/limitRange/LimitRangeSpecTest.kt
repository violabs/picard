package io.violabs.picard.domain.k8sResources.policy.limitRange


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class LimitRangeSpecTest : FailureBuildSim<LimitRange.Spec, LimitRange.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            LimitRangeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<LimitRange.Spec, LimitRange.Spec.Builder> {
            requireNotEmptyScenario("limits") {
                given(LimitRange.Spec.Builder())
            }
        }
    }
}