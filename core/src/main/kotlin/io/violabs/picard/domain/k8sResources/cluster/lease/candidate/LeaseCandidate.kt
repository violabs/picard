package io.violabs.picard.domain.k8sResources.cluster.lease.candidate

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import java.time.Instant

class LeaseCandidate(
    override val apiVersion: Version = KAPIVersion.CoordinationV1Alpha1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec
) : K8sResource<LeaseCandidate.Version> {
    interface Version : APIVersion

    data class Spec(
        val leaseName: String,
        val preferredStrategies: List<String>,
        val binaryVersion: String? = null,
        val emulationVersion: String? = null,
        val pingTime: Instant? = null,
        val renewTime: Instant? = null
    ) : BaseSpec
}