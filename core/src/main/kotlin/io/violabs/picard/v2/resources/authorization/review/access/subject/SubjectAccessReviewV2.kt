package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/subject-access-review-v1/
 *
 * SubjectAccessReview checks whether or not a user or group can perform an action.
 *
 * apiVersion: authorization.k8s.io/v1
 *
 * import "k8s.io/api/authorization/v1"
 */
@GeneratedDsl
data class SubjectAccessReviewV2(
    @DefaultValue(
        "KAPIVersion.AuthorizationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    /**
     * Standard list metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Spec holds information about the request being evaluated
     */
    val spec: SubjectAccessReviewSpec,
    /**
     * Status is filled in by the server and indicates whether the request is allowed or not
     */
    val status: SubjectAccessReviewStatus? = null
) : AuthorizationResource<SubjectAccessReviewV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

