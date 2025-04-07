package io.violabs.picard.domain.k8sResources.cluster.apiService

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.Condition

class APIService(
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<APIService.Version> {
    interface Version : APIVersion

    data class Spec(
        val groupPriorityMinimum: Int,
        val versionPriority: Int,
        val caBundle: List<Byte>? = null,
        val group: String? = null,
        val insecureSkipTLSVerify: Boolean? = null,
        val service: ServiceReference? = null,
        val version: String? = null
    ) : BaseSpec

    data class Status(
        val conditions: List<Condition>? = null
    ) : BaseStatus

    data class ServiceReference(
        val name: String? = null,
        val namespace: String? = null,
        val port: Int? = null
    )
}