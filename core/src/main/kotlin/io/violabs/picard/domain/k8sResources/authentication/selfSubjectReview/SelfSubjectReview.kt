package io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.UserInfo
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class SelfSubjectReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<SelfSubjectReview.Version> {
    interface Version : APIVersion

    data class Status(
        val userInfo: UserInfo? = null
    ) : BaseSpec
}