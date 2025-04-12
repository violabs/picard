package io.violabs.picard.domain.k8sResources.workload.pod.affinity

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeAffinityPreferredSchedulingNodeSelectorTermTest :
    FailureBuildSim<NodeAffinityPreferredSchedulingTerm, NodeAffinityPreferredSchedulingTerm.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeAffinityPreferredSchedulingNodeSelectorTermTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<NodeAffinityPreferredSchedulingTerm, NodeAffinityPreferredSchedulingTerm.Builder> {
                requireScenario("preference") {
                    given(NodeAffinityPreferredSchedulingTerm.Builder())
                }

                requireScenario("weight") {
                    given(NodeAffinityPreferredSchedulingTerm.Builder()) {
                        preference {}
                    }
                }
            }
    }
}