package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.BaseRole

interface K8sRole : BaseRole {
    val rules: List<PolicyRule>?
}