package io.violabs.picard.domain.k8sResources.config.secret

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseEnvSource
import io.violabs.picard.domain.BaseKeySelector

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/secret-v1/
 * base64 encoded string data
 */
data class Secret(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata,
    val data: BinaryData,
    val stringData: TextData,
    val immutable: Boolean = false,
    val type: Type? = null
) : K8sResource<Secret.Version> {
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
}