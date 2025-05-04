package io.violabs.picard.domain.k8sResources.workload.deployment


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeploymentSpecTest : FailureBuildSim<Deployment.Spec, Deployment.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Deployment.Spec, Deployment.Spec.Builder> {
            requireScenario("selector") {
                given(Deployment.Spec.Builder())
            }
        }
    }
}