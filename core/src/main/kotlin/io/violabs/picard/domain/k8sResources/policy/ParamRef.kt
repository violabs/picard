package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelector

data class ParamRef(
    val name: String? = null,
    val namespace: String? = null,
    val parameterNotFoundAction: String? = null,
    val selector: LabelSelector? = null,
    val policyName: String? = null,
    val validationActions: List<String>? = null,
) {
    class Builder : DSLBuilder<ParamRef> {
        var name: String? = null
        var namespace: String? = null
        var parameterNotFoundAction: String? = null
        private var selector: LabelSelector? = null
        var policyName: String? = null
        private var validationActions: List<String>? = null

        fun selector(scope: LabelSelector.Builder.() -> Unit) {
            selector = LabelSelector.Builder().apply(scope).build()
        }

        fun validationActions(vararg actions: String) {
            validationActions = actions.toList()
        }

        override fun build(): ParamRef {
            return ParamRef(
                name = name,
                namespace = namespace,
                parameterNotFoundAction = parameterNotFoundAction,
                selector = selector,
                policyName = policyName,
                validationActions = validationActions
            )
        }
    }
}