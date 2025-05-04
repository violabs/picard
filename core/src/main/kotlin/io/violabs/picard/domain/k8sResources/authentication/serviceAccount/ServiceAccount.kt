package io.violabs.picard.domain.k8sResources.authentication.serviceAccount

import io.violabs.picard.common.ResourceDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ServiceAccount(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val automountServiceAccountToken: Boolean? = null,
    val imagePullSecrets: List<LocalObjectReference>? = null,
    val secrets: List<ObjectReference>? = null
) : K8sResource<ServiceAccount.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<ServiceAccount>() {
        private var automountServiceAccountToken: Boolean? = null
        private var imagePullSecrets: List<LocalObjectReference>? = null
        private var secrets: List<ObjectReference>? = null

        fun automountServiceAccountToken(automountServiceAccountToken: Boolean = true) {
            this.automountServiceAccountToken = automountServiceAccountToken
        }

        fun imagePullSecrets(scope: LocalObjectReference.Group.() -> Unit) {
            imagePullSecrets = LocalObjectReference.Group().apply(scope).references()
        }

        fun secrets(scope: ObjectReference.Group.() -> Unit) {
            secrets = ObjectReference.Group().apply(scope).references()
        }

        override fun build(): ServiceAccount {
            return ServiceAccount(
                automountServiceAccountToken = automountServiceAccountToken,
                imagePullSecrets = imagePullSecrets,
                secrets = secrets
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ServiceAccount, Builder>(Builder()) {
        fun account(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}