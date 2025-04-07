package io.violabs.picard

data class TestScenario(
    val id: String = "",
    val description: String = "",
    val targetFields: List<String> = emptyList(),
    val skippedFields: List<String> = emptyList(),
    val content: Any? = null
)

data class TestScenarioSet(
    val scenarios: List<TestScenario> = emptyList()
)