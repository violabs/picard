package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authorization-resources/self-subject-rules-review-v1/
 *
 * SelfSubjectRulesReview enumerates the set of actions the current user
 * can perform within a namespace. The returned list of actions may be incomplete
 * depending on the server's authorization mode, and any errors experienced during
 * the evaluation. SelfSubjectRulesReview should be used by UIs to show/hide actions,
 * or to quickly let an end user reason about their permissions. It should NOT Be used
 * by external systems to drive authorization decisions as this raises confused deputy,
 * cache lifetime/revocation, and correctness concerns. SubjectAccessReview, and
 * LocalAccessReview are the correct way to defer authorization decisions to the API server.
 *
 * apiVersion: authorization.k8s.io/v1
 *
 * import "k8s.io/api/authorization/v1"
 */
@GeneratedDsl
data class SelfSubjectRulesReviewV2(
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
     * Spec holds information about the request being evaluated.
     */
    val spec: SelfSubjectRulesReviewSpec,
    /**
     * Status is filled in by the server and indicates the set of actions a user can perform.
     */
    val status: SelfSubjectRulesReviewStatus? = null
) : AuthorizationResource<SelfSubjectRulesReviewV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

