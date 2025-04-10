package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapEnvSource
import io.violabs.picard.domain.k8sResources.config.secret.SecretEnvSource

data class EnvFromSource(
    val configMapRef: ConfigMapEnvSource? = null,
    val prefix: String? = null,
    val secretRef: SecretEnvSource? = null
) {
    class Builder : DslBuilder<EnvFromSource> {
        private var configMapRef: ConfigMapEnvSource? = null
        var prefix: String? = null
        private var secretRef: SecretEnvSource? = null

        fun configMapRef(scope: ConfigMapEnvSource.Builder.() -> Unit) {
            this.configMapRef = ConfigMapEnvSource.Builder().apply(scope).build()
        }

        fun secretRef(scope: SecretEnvSource.Builder.() -> Unit) {
            this.secretRef = SecretEnvSource.Builder().apply(scope).build()
        }

        override fun build(): EnvFromSource {
            return EnvFromSource(
                configMapRef = configMapRef,
                prefix = prefix,
                secretRef = secretRef
            )
        }
    }
}