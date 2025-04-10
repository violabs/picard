package io.violabs.picard

data class TestScenario<T, U>(
    var id: String = "",
    var description: String = "",
    var targetFields: List<String> = emptyList(),
    var skippedFields: List<String> = emptyList(),
    var given: U? = null,
    var expected: T? = null,
    var exceptionMessage: ExceptionMessage? = null
) {
    fun given(obj: U, scope: U.() -> Unit = {}) {
        given = obj.apply(scope)
    }
}

data class TestScenarioSet<T, U>(
    val scenarios: MutableList<TestScenario<T, U>> = mutableListOf(),
    val exceptionTemplate: String = "%s must not be null"
) {

    fun scenario(scope: TestScenario<T, U>.() -> Unit) {
        scenarios += TestScenario<T, U>().apply(scope)
    }

    fun withTemplate(messagePart: String): ExceptionMessage = ExceptionMessage(exceptionTemplate.format(messagePart))
}

fun <T, U> possibilities(template: String? = null, scope: TestScenarioSet<T, U>.() -> Unit): TestScenarioSet<T, U> {
    val set = template?.let { TestScenarioSet<T, U>(exceptionTemplate = it) } ?: TestScenarioSet()

    return set.also(scope)
}

data class ExceptionMessage(val content: String)