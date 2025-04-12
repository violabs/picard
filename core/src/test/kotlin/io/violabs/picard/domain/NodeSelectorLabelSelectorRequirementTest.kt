package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeSelectorLabelSelectorRequirementTest : FailureBuildSim<NodeSelectorRequirement, NodeSelectorRequirement.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeSelectorLabelSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<NodeSelectorRequirement, NodeSelectorRequirement.Builder> {
    requireScenario("key") {
        id = "missing key"
        given(NodeSelectorRequirement.Builder())
    }

    requireScenario("operator") {
        given(NodeSelectorRequirement.Builder()) {
            key = "test-key"
        }
    }
}