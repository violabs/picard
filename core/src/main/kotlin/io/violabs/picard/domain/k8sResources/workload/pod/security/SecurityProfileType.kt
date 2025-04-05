package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.domain.K8sEnum

enum class SecurityProfileType : K8sEnum {
    LOCALHOST,
    RUNTIME_DEFAULT,
    UNCONFINED;

    override fun toString(): String = properCase()
}