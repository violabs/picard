package io.violabs.picard.domain.k8sResources.policy.subject

import io.violabs.picard.domain.BaseSubject

data class UserSubject(
    val name: String
) : BaseSubject