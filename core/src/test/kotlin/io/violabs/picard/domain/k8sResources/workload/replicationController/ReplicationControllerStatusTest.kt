package io.violabs.picard.domain.k8sResources.workload.replicationController


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerStatusTest :
    FailureBuildSim<ReplicationControllerStatus, ReplicationControllerStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ReplicationControllerStatus, ReplicationControllerStatusDslBuilder> {
                requireScenario("replicas") {
                    given(ReplicationControllerStatusDslBuilder())
                }
            }
    }
}