package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.APIVersion
import io.violabs.picard.domain.Kind

interface K8sResource<T : APIVersion> {
    val apiVersion: T
    val kind: Kind
}