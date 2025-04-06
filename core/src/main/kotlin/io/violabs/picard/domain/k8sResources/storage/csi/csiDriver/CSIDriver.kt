package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

data class CSIDriver(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<CSIDriver.Version> {
    interface Version : APIVersion

    data class Spec(
        val attachRequired: Boolean,
        val podInfoOnMount: Boolean,
        val requiresRepublish: Boolean,
        val seLinuxMount: Boolean,
        val storageCapacity: Int,
        val fsGroupPolicy: String,
        val tokenRequests: List<TokenRequest>,
        val volumeLifecycleModes: List<String>
    ) : BaseSpec
}