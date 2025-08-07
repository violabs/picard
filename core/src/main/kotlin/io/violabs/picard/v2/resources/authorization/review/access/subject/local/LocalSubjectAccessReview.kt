package io.violabs.picard.v2.resources.authorization.review.access.subject.local

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewSpec
import io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewStatus

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/local-subject-access-review-v1/
 *
 * LocalSubjectAccessReview checks whether or not a user or group can
 * perform an action in a given namespace. Having a namespace scoped
 * resource makes it much easier to grant namespace scoped policy
 * that includes permissions checking.
 *
 * apiVersion: authorization.k8s.io/v1
 *
 * import "k8s.io/api/authorization/v1"
 */
@GeneratedDsl
data class LocalSubjectAccessReview(
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
     * Spec holds information about the request being evaluated. spec.namespace
     * must be equal to the namespace you made the request against. If empty, it is defaulted.
     */
    val spec: SubjectAccessReviewSpec,
    /**
     * Status is filled in by the server and indicates whether the request is allowed or not
     */
    val status: SubjectAccessReviewStatus? = null
) : AuthorizationResource<LocalSubjectAccessReview.Version, ObjectMeta> {
    interface Version : APIVersion
}