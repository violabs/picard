package io.violabs.picard.domain.k8sResources.cluster.apiService

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource

data class APIService(
    override val apiVersion: Version = KAPIVersion.APIRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : ClusterResource<APIService.Version> {
    interface Version : APIVersion

    data class Spec(
        val groupPriorityMinimum: Int,
        val versionPriority: Int,
        val caBundle: List<Byte>? = null,
        val group: String? = null,
        val insecureSkipTLSVerify: Boolean? = null,
        val service: ServiceReference? = null,
        val version: String? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var groupPriorityMinimum: Int? = null
            var versionPriority: Int? = null
            private var caBundle: List<Byte>? = null
            var group: String? = null
            private var insecureSkipTLSVerify: Boolean? = null
            private var service: ServiceReference? = null
            var version: String? = null

            fun caBundle(vararg caBundle: Byte) {
                this.caBundle = caBundle.toList()
            }

            fun insecureSkipTLSVerify(value: Boolean = true) {
                insecureSkipTLSVerify = value
            }

            fun service(scope: ServiceReference.Builder.() -> Unit) {
                service = ServiceReference.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    groupPriorityMinimum = vRequireNotNull(this::groupPriorityMinimum),
                    versionPriority = vRequireNotNull(this::versionPriority),
                    caBundle = caBundle,
                    group = group,
                    insecureSkipTLSVerify = insecureSkipTLSVerify,
                    service = service,
                    version = version
                )
            }
        }
    }

    data class Status(
        val conditions: List<Condition>? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var conditions: List<Condition>? = null

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            override fun build(): Status {
                return Status(
                    conditions = conditions,
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        APIService,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder
        >(Spec.Builder(), Status.Builder()) {
        override fun build(): APIService {
            return APIService(
                metadata = metadata,
                spec = spec,
                status = status,
            )
        }
    }

    class Group : K8sListResource.ItemGroup<APIService, Builder>(Builder()) {
        fun service(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}