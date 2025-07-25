package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class Variable(
    val expression: String,
    val name: String
) {
    class Builder : DslBuilder<Variable> {
        var expression: String? = null
        var name: String? = null

        override fun build(): Variable {
            return Variable(
                expression = vRequireNotNull(this::expression),
                name = vRequireNotNull(this::name)
            )
        }
    }

    class Group : BuilderGroup<Variable, Builder>(Builder()) {
        fun variables(): List<Variable>? = items()

        fun addVariable(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}