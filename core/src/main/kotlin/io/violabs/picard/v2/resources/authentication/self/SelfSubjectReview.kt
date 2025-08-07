package io.violabs.picard.v2.resources.authentication.self

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/self-subject-review-v1/
 *
 * SelfSubjectReview contains the user information that the kube-apiserver has
 * about the user making this request. When using impersonation, users will receive
 * the user info of the user being impersonated. If impersonation or request header
 * authentication is used, any extra keys will have their case ignored and returned as lowercase.
 *
 * apiVersion: authentication.k8s.io/v1
 *
 * import "k8s.io/api/authentication/v1"
 */
@GeneratedDsl(withListGroup = true)
data class SelfSubjectReview(
    @DefaultValue(
        "KAPIVersion.AuthenticationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Status is filled in by the server with the user attributes.
     */
    val status: SelfSubjectReviewStatus? = null
) : AuthenticationResource<SelfSubjectReview.Version, ObjectMeta> {
    interface Version : APIVersion
}

