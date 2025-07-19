package io.violabs.picard.domain.k8sResources.authentication.serviceAccount

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.LocalObjectReference
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource

data class ServiceAccount(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val automountServiceAccountToken: Boolean? = null,
    val imagePullSecrets: List<LocalObjectReference>? = null,
    val secrets: List<ObjectReference>? = null
) : AuthenticationResource<ServiceAccount.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ServiceAccount>() {
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