package io.violabs.picard.domain.k8sResources.workload


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class RollingUpdateTest : SuccessBuildSim<BaseStrategy.RollingUpdate, BaseStrategy.RollingUpdate.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            RollingUpdateTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<BaseStrategy.RollingUpdate, BaseStrategy.RollingUpdate.Builder> {
                scenario {
                    id = "string based"
                    given(BaseStrategy.RollingUpdate.Builder()) {
                        maxSurge("1")
                        maxUnavailable("1")
                    }
                    expected = BaseStrategy.RollingUpdate(
                        maxSurge = 1,
                        maxUnavailable = 1
                    )
                }
            }
    }
}