package io.violabs.picard.domain.k8sResources.workload.pod.affinity


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeAffinityTest : SuccessBuildSim<NodeAffinity, NodeAffinityDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeAffinityTest::class,
            SUCCESS_POSSIBILITIES
        )
    }
}

private val SUCCESS_POSSIBILITIES = possibilities<NodeAffinity, NodeAffinityDslBuilder> {
    scenario {
        id = "empty group"
        given(NodeAffinityDslBuilder()) {
            preferredDuringSchedulingIgnoredDuringExecution {  }
        }
        expected = NodeAffinity()
    }
}