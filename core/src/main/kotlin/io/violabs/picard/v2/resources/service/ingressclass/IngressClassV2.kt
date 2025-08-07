package io.violabs.picard.v2.resources.service.ingressclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * IngressClass represents the class of the Ingress, referenced by the Ingress Spec.
 * The ingressclass.kubernetes.io/is-default-class annotation can be used to indicate that an 
 * IngressClass should be considered default. When a single IngressClass resource has this 
 * annotation set to true, new Ingress resources without a class specified will be assigned 
 * this default class.
 *
 * apiVersion: networking.k8s.io/v1
 * kind: IngressClass
 *
 * Standard object's metadata. More info: 
 * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
 */
@GeneratedDsl(withListGroup = true)
data class IngressClassV2(
    @DefaultValue(
        "KAPIVersion.NetworkingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the desired state of the IngressClass. More info: 
     * https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: IngressClassSpec? = null
) : ServiceResource<IngressClassV2.Version, ObjectMeta> {
    interface Version : APIVersion
}