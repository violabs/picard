package io.violabs.picard.domain.k8sResources.extend.customResource.customResourceDefinition

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceConversion
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionNames
import io.violabs.picard.domain.k8sResources.extend.customResource.CustomResourceDefinitionVersion

data class CustomResourceDefinition(
    override val apiVersion: Version = KAPIVersion.APIExtensionsV1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<CustomResourceDefinition.Version> {
    interface Version : APIVersion

    data class Spec(
        val group: String,
        val names: CustomResourceDefinitionNames,
        val scope: String,
        val versions: List<CustomResourceDefinitionVersion>,
        val conversion: CustomResourceConversion? = null,
        val preserveUnknownFields: Boolean? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var group: String? = null
            private var names: CustomResourceDefinitionNames? = null
            var scope: String? = null
            private var versions: List<CustomResourceDefinitionVersion>? = null
            private var conversion: CustomResourceConversion? = null
            private var preserveUnknownFields: Boolean? = null

            fun names(block: CustomResourceDefinitionNames.Builder.() -> Unit) {
                this.names = CustomResourceDefinitionNames.Builder().apply(block).build()
            }

            fun versions(block: CustomResourceDefinitionVersion.Builder.() -> Unit) {
                this.versions = listOf(CustomResourceDefinitionVersion.Builder().apply(block).build())
            }

            fun conversion(block: CustomResourceConversion.Builder.() -> Unit) {
                this.conversion = CustomResourceConversion.Builder().apply(block).build()
            }

            fun preserveUnknownFields(value: Boolean = true) {
                this.preserveUnknownFields = value
            }

            override fun build(): Spec {
                return Spec(
                    group = vRequireNotNull(this::group),
                    names = vRequireNotNull(this::names),
                    scope = vRequireNotNull(this::scope),
                    versions = vRequireNotNull(this::versions),
                    conversion = conversion,
                    preserveUnknownFields = preserveUnknownFields
                )
            }
        }
    }

    data class Status(
        val acceptedNames: CustomResourceDefinitionNames? = null,
        val conditions: List<Condition>? = null,
        val storedVersions: List<String>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var acceptedNames: CustomResourceDefinitionNames? = null
            private var conditions: List<Condition>? = null
            private var storedVersions: List<String>? = null

            fun acceptedNames(block: CustomResourceDefinitionNames.Builder.() -> Unit) {
                this.acceptedNames = CustomResourceDefinitionNames.Builder().apply(block).build()
            }

            fun conditions(block: ConditionGroup<Condition, Condition.Builder>.() -> Unit) {
                this.conditions = ConditionGroup(Condition.Builder()).apply(block).conditions()
            }

            fun storedVersions(vararg versions: String) {
                this.storedVersions = versions.toList()
            }

            override fun build(): Status {
                return Status(
                    acceptedNames = acceptedNames,
                    conditions = conditions,
                    storedVersions = storedVersions
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        CustomResourceDefinition,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): CustomResourceDefinition {
            return CustomResourceDefinition(
                spec = vRequireNotNull(this::spec),
                status = status,
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CustomResourceDefinition, Builder>(Builder()) {
        fun definition(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}