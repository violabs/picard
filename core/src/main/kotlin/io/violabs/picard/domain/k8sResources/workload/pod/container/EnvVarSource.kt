package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.ObjectFieldSelector
import io.violabs.picard.domain.ResourceFieldSelector
import io.violabs.picard.domain.k8sResources.config.configMap.ConfigMapKeySelector
import io.violabs.picard.domain.k8sResources.config.secret.SecretKeySelector

data class EnvVarSource(
    val configMapKeyRef: ConfigMapKeySelector? = null,
    val fieldRef: ObjectFieldSelector? = null,
    val resourceFieldRef: ResourceFieldSelector? = null,
    val secretKeyRef: SecretKeySelector? = null
) {
    class Builder : DSLBuilder<EnvVarSource> {
        private var configMapKeyRef: ConfigMapKeySelector? = null
        private var fieldRef: ObjectFieldSelector? = null
        private var resourceFieldRef: ResourceFieldSelector? = null
        private var secretKeyRef: SecretKeySelector? = null

        fun configMapKeyRef(scope: ConfigMapKeySelector.Builder.() -> Unit) {
            configMapKeyRef = ConfigMapKeySelector.Builder().apply(scope).build()
        }

        fun fieldRef(scope: ObjectFieldSelector.Builder.() -> Unit) {
            fieldRef = ObjectFieldSelector.Builder().apply(scope).build()
        }

        fun resourceFieldRef(scope: ResourceFieldSelector.Builder.() -> Unit) {
            resourceFieldRef = ResourceFieldSelector.Builder().apply(scope).build()
        }

        fun secretKeyRef(scope: SecretKeySelector.Builder.() -> Unit) {
            secretKeyRef = SecretKeySelector.Builder().apply(scope).build()
        }

        override fun build(): EnvVarSource {
            return EnvVarSource(
                configMapKeyRef = configMapKeyRef,
                fieldRef = fieldRef,
                resourceFieldRef = resourceFieldRef,
                secretKeyRef = secretKeyRef
            )
        }
    }
}