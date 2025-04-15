package io.violabs.picard.domain.k8sResources.authentication.clusterTrustBundle


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleSpecTest : FailureBuildSim<ClusterTrustBundle.Spec, ClusterTrustBundle.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundle.Spec, ClusterTrustBundle.Spec.Builder> {
            requireScenario("trustBundle") {
                given(ClusterTrustBundle.Spec.Builder())
            }
        }
    }
}