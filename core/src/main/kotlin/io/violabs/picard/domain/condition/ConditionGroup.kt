package io.violabs.picard.domain.condition

import io.violabs.picard.domain.BaseCondition
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

class ConditionGroup<T : BaseCondition, B : DslBuilder<T>>(baseBuilder: B) : BuilderGroup<T, B>(baseBuilder) {
    fun conditions(): MutableList<T>? = items()

    fun condition(scope: B.() -> Unit) {
        add(scope)
    }
}