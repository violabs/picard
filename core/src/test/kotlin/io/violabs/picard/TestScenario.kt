package io.violabs.picard

data class TestScenario<T, BUILDER>(
    var id: String = "",
    var description: String = "",
    var targetFields: List<String> = emptyList(),
    var skippedFields: List<String> = emptyList(),
    var given: BUILDER? = null,
    var expected: T? = null,
    var exceptionMessage: ExceptionMessage? = null
) {
    fun given(obj: BUILDER, scope: BUILDER.() -> Unit = {}) {
        given = obj.apply(scope)
    }
}

data class TestScenarioSet<T, BUILDER>(
    val scenarios: MutableList<TestScenario<T, BUILDER>> = mutableListOf(),
    val exceptionTemplate: String = "%s must not be null"
) {

    fun scenario(scope: TestScenario<T, BUILDER>.() -> Unit) {
        scenarios += TestScenario<T, BUILDER>().apply(scope)
    }

    fun withTemplate(messagePart: String): ExceptionMessage = ExceptionMessage(exceptionTemplate.format(messagePart))
}

fun <T, BUILDER> possibilities(
    template: String? = null,
    scope: TestScenarioSet<T, BUILDER>.() -> Unit
): TestScenarioSet<T, BUILDER> {
    val set = template?.let { TestScenarioSet<T, BUILDER>(exceptionTemplate = it) } ?: TestScenarioSet()

    return set.also(scope)
}

data class ExceptionMessage(val content: String)