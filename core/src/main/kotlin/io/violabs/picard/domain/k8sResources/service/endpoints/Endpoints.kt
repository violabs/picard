package io.violabs.picard.domain.k8sResources.service.endpoints

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.service.Endpoint


data class Endpoints(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val subsets: List<Endpoint.Subset>? = null
) : K8sResource<Endpoints.Version> {
    interface Version : APIVersion
}
