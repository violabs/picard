package io.violabs.picard.domain.k8sResources.policy.subject

import io.violabs.picard.domain.BaseSubject

data class ServiceAccountSubject(
    val name: String,
    val namespace: String
) : BaseSubject
