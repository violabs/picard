package io.violabs.picard.v2.resources.authorization.review.access.subject.self

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewStatus

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/self-subject-access-review-v1/
 *
 * SelfSubjectAccessReview checks whether or the current user can perform an action.
 * Not filling in a spec.namespace means "in all namespaces". Self is a special case,
 * because users should always be able to check whether they can perform an action
 *
 * apiVersion: authorization.k8s.io/v1
 *
 * import "k8s.io/api/authorization/v1"
 */
@GeneratedDsl
data class SelfSubjectAccessReviewV2(
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
     * Spec holds information about the request being evaluated. user and groups must be empty
     */
    val spec: SelfSubjectAccessReviewSpec,
    /**
     * Status is filled in by the server and indicates whether the request is allowed or not
     */
    val status: SubjectAccessReviewStatus? = null
) : AuthorizationResource<SelfSubjectAccessReviewV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

