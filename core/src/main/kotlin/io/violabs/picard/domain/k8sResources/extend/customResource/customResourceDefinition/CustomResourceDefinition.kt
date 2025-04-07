package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceConversion
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionNames
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionVersion
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.Condition

class CustomResourceDefinition(
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<CustomResourceDefinition.Version> {
    interface Version : APIVersion

    data class Spec(
        val group: String,
        val names: CustomResourceDefinitionNames,
        val scope: String,
        val versions: List<CustomResourceDefinitionVersion>,
        val conversion: CustomResourceConversion? = null,
        val preserveUnknownFields: Boolean? = null
    ) : BaseSpec

    data class Status(
        val acceptedNames: CustomResourceDefinitionNames? = null,
        val conditions: List<Condition>? = null,
        val storedVersions: List<String>? = null
    ) : BaseSpec
}