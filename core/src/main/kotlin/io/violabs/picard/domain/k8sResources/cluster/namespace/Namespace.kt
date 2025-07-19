package io.violabs.picard.domain.k8sResources.cluster.namespace

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.condition.Condition
import io.violabs.picard.domain.condition.StandardConditionGroup
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource

data class Namespace(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : ClusterResource<Namespace.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val finalizers: List<String>? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var finalizers: List<String>? = null

            fun finalizers(vararg finalizers: String) {
                this.finalizers = finalizers.toList()
            }

            override fun build(): Spec {
                return Spec(finalizers = finalizers)
            }
        }
    }

    data class Status(
        val conditions: List<Condition>? = null,
        val phase: String? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var conditions: List<Condition>? = null
            var phase: String? = null

            fun conditions(scope: StandardConditionGroup.() -> Unit) {
                conditions = Condition.group(scope)
            }

            override fun build(): Status {
                return Status(conditions = conditions, phase = phase)
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        Namespace,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(
        Spec.Builder(),
        Status.Builder()
    ) {
        override fun build(): Namespace {
            return Namespace(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Namespace, Builder>(Builder()) {
        fun name(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}