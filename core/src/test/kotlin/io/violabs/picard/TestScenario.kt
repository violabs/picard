package io.violabs.picard

data class TestScenario<T, U>(
    var id: String = "",
    var description: String = "",
    var targetFields: List<String> = emptyList(),
    var skippedFields: List<String> = emptyList(),
    var given: U? = null,
    var expected: T? = null
) {
    fun given(obj: U, scope: U.() -> Unit) {
        given = obj.apply(scope)
    }
}

data class TestScenarioSet<T, U>(
    val scenarios: MutableList<TestScenario<T, U>> = mutableListOf()
) {

    fun scenario(scope: TestScenario<T, U>.() -> Unit) {
        scenarios += TestScenario<T, U>().apply(scope)
    }
}

fun <T, U> possibilities(scope: TestScenarioSet<T, U>.() -> Unit): TestScenarioSet<T, U> {
    return TestScenarioSet<T, U>().also(scope)
}