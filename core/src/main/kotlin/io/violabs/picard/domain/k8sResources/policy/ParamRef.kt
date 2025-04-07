package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.LabelSelector

data class ParamRef(
    val name: String? = null,
    val namespace: String? = null,
    val parameterNotFoundAction: String? = null,
    val selector: LabelSelector? = null,
    val policyName: String? = null,
    val validationActions: List<String>? = null,
)