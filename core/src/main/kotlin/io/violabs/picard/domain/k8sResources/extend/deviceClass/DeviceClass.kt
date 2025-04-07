package io.violabs.picard.domain.k8sResources.extend.deviceClass

import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.NodeSelector

class DeviceClass(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec
) : K8sResource<DeviceClass.Version> {
    interface Version : APIVersion

    data class Spec(
        val config: List<DeviceClassConfiguration>? = null,
        val selectors: List<DeviceSelector>? = null,
        val suitableNodes: NodeSelector? = null
    ) : BaseSpec
}