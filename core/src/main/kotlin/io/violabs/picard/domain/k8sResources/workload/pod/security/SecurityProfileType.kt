package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.ExcludeFromCoverage


@ExcludeFromCoverage
enum class SecurityProfileType {
    Localhost,
    RuntimeDefault,
    Unconfined
}
