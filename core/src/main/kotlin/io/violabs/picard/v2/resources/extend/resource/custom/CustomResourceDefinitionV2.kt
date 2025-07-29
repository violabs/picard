package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * CustomResourceDefinition represents a resource that should be exposed on the API server.
 * Its name MUST be in the format <.spec.names.plural>.<.spec.group>.
 * 
 * apiVersion: apiextensions.k8s.io/v1
 * 
 * import "k8s.io/apiextensions-apiserver/pkg/apis/apiextensions/v1"
 * 
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/custom-resource-definition-v1/
 */
@GeneratedDsl
data class CustomResourceDefinitionV2(
    @DefaultValue(
        "KAPIVersion.APIExtensionsV1", 
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE, 
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * spec describes how the user wants the resources to appear
     */
    val spec: CustomResourceDefinitionSpec? = null,
    /**
     * status indicates the actual state of the CustomResourceDefinition
     */
    val status: CustomResourceDefinitionStatus? = null
) : ExtendResource<CustomResourceDefinitionV2.Version, ObjectMeta> {
    interface Version : APIVersion
}