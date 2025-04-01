package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.*

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/secret-v1/
 * base64 encoded string data
 */
data class Secret(
    override val apiVersion: Version,
    val metadata: ObjectMetadata,
    val data: BinaryData,
    val stringData: TextData,
    val immutable: Boolean = false,
    val type: Type? = null
) : K8sResource<Secret.Version> {
    override val kind: Kind = Kind.SECRET

    enum class Version(override val ref: String? = null) : APIVersion {
        V1;

        override fun toString(): String = refString()
    }

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
}