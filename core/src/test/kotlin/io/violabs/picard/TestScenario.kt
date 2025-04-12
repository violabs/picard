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

    fun idForFalseBooleanValues() {
        id = "false boolean values"
    }
}

data class TestScenarioSet<T, BUILDER>(
    val scenarios: MutableList<TestScenario<T, BUILDER>> = mutableListOf(),
    val exceptionTemplate: String = "%s is required"
) {

    fun scenario(scope: TestScenario<T, BUILDER>.() -> Unit) {
        scenarios += TestScenario<T, BUILDER>().apply(scope)
    }

    fun requireScenario(fieldName: String, scope: TestScenario<T, BUILDER>.() -> Unit) {
        scenarios += TestScenario<T, BUILDER>().apply(scope).apply {
            id = "missing $fieldName"
            exceptionMessage = withTemplate(fieldName)
        }
    }

    fun requireNotEmptyScenario(fieldName: String, scope: TestScenario<T, BUILDER>.() -> Unit) {
        scenarios += TestScenario<T, BUILDER>().apply(scope).apply {
            id = "missing or empty $fieldName"
            exceptionMessage = ExceptionMessage("$fieldName is required and cannot be empty")
        }
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