package io.violabs.picard.domain.k8sResources.config.secret

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/secret-v1/
 * base64 encoded string data
 */
@Deprecated("Use v2 version", ReplaceWith("io.violabs.picard.v2.resources.config.secret.SecretV2"))
@JsonPropertyOrder("apiVersion", "kind", "metadata", "type", "data", "immutable")
data class Secret(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val data: BinaryData? = null,
    val stringData: TextData? = null,
    val immutable: Boolean? = null,
    val type: Type? = null
) : ConfigResource<Secret.Version, ObjectMetadata> {
    interface Version : APIVersion

    enum class Type(private val ref: String) {
        OPAQUE("Opaque"),
        SERVICE_ACCOUNT_TOKEN("kubernetes.io/service-account-token"),
        DOCKER_CONFIG("kubernetes.io/dockercfg"),
        DOCKER_CONFIG_JSON("kubernetes.io/dockerconfigjson"),
        BASIC_AUTH("kubernetes.io/basic-auth"),
        SSH_AUTH("kubernetes.io/ssh-auth"),
        TLS("kubernetes.io/tls"),
        TOKEN("kubernetes.io/token");

        override fun toString(): String = ref
    }

    class Builder : ResourceDslBuilder<Secret>() {
        private var data: BinaryData? = null
        private var stringData: TextData? = null
        private var immutable: Boolean? = null
        var type: Type? = null

        fun data(vararg data: Pair<String, String>) {
            this.data = BinaryData(data.toMap().toMutableMap())
        }

        fun data(type: BinaryData.Type, vararg data: Pair<String, String>) {
            this.data = BinaryData(data.toMap().toMutableMap(), type)
        }

        fun stringData(vararg data: Pair<String, String>) {
            this.stringData = TextData(data.toMap().toMutableMap())
        }

        fun immutable(value: Boolean = true) {
            this.immutable = value
        }

        override fun build(): Secret {
            return Secret(
                metadata = metadata,
                data = data,
                stringData = stringData,
                immutable = immutable,
                type = type
            )
        }
    }

    class Group : K8sListResource.ItemGroup<Secret, Builder>(Builder()) {
        fun secretItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}