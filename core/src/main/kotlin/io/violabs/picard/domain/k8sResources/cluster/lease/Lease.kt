package io.violabs.picard.domain.k8sResources.cluster.lease

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import java.time.Instant

class Lease(
    override val apiVersion: Version = KAPIVersion.CoordinationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<Lease.Version> {
    interface Version : APIVersion

    data class Spec(
        val acquireTime: Instant? = null,
        val holderIdentity: String? = null,
        val leaseDurationSeconds: Int? = null,
        val leaseTransitions: Int? = null,
        val preferredHolder: String? = null,
        val renewTime: Instant? = null,
        val strategy: String? = null
    )
}