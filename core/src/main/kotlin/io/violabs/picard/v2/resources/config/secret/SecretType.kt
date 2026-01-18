package io.violabs.picard.v2.resources.config.secret

import com.fasterxml.jackson.annotation.JsonValue

enum class SecretType(@get:JsonValue val ref: String) {
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