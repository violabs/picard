package io.violabs.picard.domain.k8sResources.workload.pod.affinity


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeAffinityTest : SuccessBuildSim<NodeAffinity, NodeAffinity.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeAffinityTest::class,
            SUCCESS_POSSIBILITIES
        )
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<NodeAffinity, NodeAffinity.Builder> {
    scenario {
        id = "empty group"
        given(NodeAffinity.Builder()) {
            preferredDuringSchedulingIgnoredDuringExecution {  }
        }
        expected = NodeAffinity()
    }
}