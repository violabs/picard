package io.violabs.picard.domain.k8sResources.workload.deployment


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeploymentStatusTest : FailureBuildSim<DeploymentStatus, DeploymentStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentStatusTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<DeploymentStatus, DeploymentStatusDslBuilder> {
            requireScenario("replicas") {
                given(DeploymentStatusDslBuilder())
            }
        }
    }
}