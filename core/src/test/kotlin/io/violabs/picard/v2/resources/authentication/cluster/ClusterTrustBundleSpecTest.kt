package io.violabs.picard.v2.resources.authentication.cluster

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ClusterTrustBundleSpecTest : FailureBuildSim<ClusterTrustBundleSpec, ClusterTrustBundleSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ClusterTrustBundleSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ClusterTrustBundleSpec, ClusterTrustBundleSpecDslBuilder> {
            requireScenario("trustBundle") {
                given(ClusterTrustBundleSpecDslBuilder())
            }
        }
    }
}