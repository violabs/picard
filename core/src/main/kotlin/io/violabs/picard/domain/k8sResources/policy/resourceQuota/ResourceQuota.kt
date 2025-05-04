package io.violabs.picard.domain.k8sResources.policy.resourceQuota

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.*
import io.violabs.picard.domain.k8sResources.policy.ScopeSelector

data class ResourceQuota(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<ResourceQuota.Version> {
    interface Version : APIVersion

    data class Spec(
        val hard: Map<String, Quantity>? = null,
        val scopeSelector: ScopeSelector? = null,
        val scopes: List<String>? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var hard: Map<String, Quantity>? = null
            private var scopeSelector: ScopeSelector? = null
            private var scopes: List<String>? = null

            fun hard(vararg hard: Pair<String, Quantity>) {
                this.hard = hard.toMap()
            }

            fun scopeSelector(scope: ScopeSelector.Builder.() -> Unit) {
                this.scopeSelector = ScopeSelector.Builder().apply(scope).build()
            }

            fun scopes(vararg scopes: String) {
                this.scopes = scopes.toList()
            }

            override fun build(): Spec {
                return Spec(
                    hard = hard,
                    scopeSelector = scopeSelector,
                    scopes = scopes
                )
            }
        }
    }
    
    data class Status(
        val hard: Map<String, Quantity>? = null,
        val used: Map<String, Quantity>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var hard: Map<String, Quantity>? = null
            private var used: Map<String, Quantity>? = null

            fun hard(vararg hard: Pair<String, Quantity>) {
                this.hard = hard.toMap()
            }

            fun used(vararg used: Pair<String, Quantity>) {
                this.used = used.toMap()
            }

            override fun build(): Status {
                return Status(
                    hard = hard,
                    used = used
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ResourceQuota,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {

        override fun build(): ResourceQuota {
            return ResourceQuota(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ResourceQuota, Builder>(Builder()) {
        fun quota(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}