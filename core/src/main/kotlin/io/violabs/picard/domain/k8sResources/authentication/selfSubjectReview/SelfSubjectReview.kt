package io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceStatusDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.UserInfo

data class SelfSubjectReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<SelfSubjectReview.Version> {
    interface Version : APIVersion

    data class Status(
        val userInfo: UserInfo? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var userInfo: UserInfo? = null

            fun userInfo(scope: UserInfo.Builder.() -> Unit) {
                userInfo = UserInfo.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    userInfo = userInfo
                )
            }
        }
    }

    class Builder : ResourceStatusDSLBuilder<SelfSubjectReview, Status, Status.Builder>(Status.Builder()) {
        override fun build(): SelfSubjectReview {
            return SelfSubjectReview(
                status = status,
                metadata = metadata
            )
        }
    }
}