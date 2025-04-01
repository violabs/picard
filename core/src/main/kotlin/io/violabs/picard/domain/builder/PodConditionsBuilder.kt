package io.violabs.picard.domain.builder

import io.violabs.picard.domain.PodCondition

class PodConditionsBuilder : Builder<List<PodCondition>> {
    private val conditions: MutableList<PodCondition> = mutableListOf()

    override fun build(): List<PodCondition> = conditions

    fun condition(scope: PodConditionBuilder.() -> Unit) {
        conditions.add(scopedBuild(::PodConditionBuilder, scope))
    }
}