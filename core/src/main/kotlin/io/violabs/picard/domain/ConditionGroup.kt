package io.violabs.picard.domain

class ConditionGroup<T : BaseCondition, B : DslBuilder<T>>(baseBuilder: B) : BuilderGroup<T, B>(baseBuilder) {
    fun conditions(): MutableList<T>? = items()

    fun condition(scope: B.() -> Unit) {
        add(scope)
    }
}