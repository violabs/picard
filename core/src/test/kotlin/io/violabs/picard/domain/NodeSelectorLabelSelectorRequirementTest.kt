package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeSelectorLabelSelectorRequirementTest : FailureBuildSim<NodeSelectorRequirement, NodeSelectorRequirementDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeSelectorLabelSelectorRequirementTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
    }
}

private val FAILURE_POSSIBILITIES = possibilities<NodeSelectorRequirement, NodeSelectorRequirementDslBuilder> {
    requireScenario("key") {
        id = "missing key"
        given(NodeSelectorRequirementDslBuilder())
    }

    requireScenario("operator") {
        given(NodeSelectorRequirementDslBuilder()) {
            key = "test-key"
        }
    }
}