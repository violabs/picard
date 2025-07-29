package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * CustomResourceSubresources defines the status and scale subresources for CustomResources.
 */
@GeneratedDsl
data class CustomResourceSubresources(
    /**
     * scale indicates the custom resource should serve a /scale subresource that returns an autoscaling/v1 Scale object.
     */
    val scale: CustomResourceSubresourceScale? = null,
    /**
     * status indicates the custom resource should serve a /status subresource. When enabled:
     * 1. requests to the custom resource primary endpoint ignore changes to the status stanza of the object.
     * 2. requests to the custom resource /status subresource ignore changes to anything other than the status stanza of the object.
     */
    val status: CustomResourceSubresourceStatus? = null
)