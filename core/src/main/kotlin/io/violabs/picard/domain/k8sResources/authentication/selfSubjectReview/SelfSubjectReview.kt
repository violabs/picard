package io.violabs.picard.domain.k8sResources.authentication.selfSubjectReview

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceStatusDslBuilder
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.UserInfo
import io.violabs.picard.domain.manifest.AuthenticationResource

data class SelfSubjectReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : AuthenticationResource<SelfSubjectReview.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Status(
        val userInfo: UserInfo? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
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

    class Builder : ResourceStatusDslBuilder<SelfSubjectReview, Status, Status.Builder>(Status.Builder()) {
        override fun build(): SelfSubjectReview {
            return SelfSubjectReview(
                status = status,
                metadata = metadata
            )
        }
    }
}