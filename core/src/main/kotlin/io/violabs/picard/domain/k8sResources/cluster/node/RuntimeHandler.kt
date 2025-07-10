package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class RuntimeHandler(
    val features: RuntimeHandlerFeatures? = null,
    val name: String? = null
) {
    class Builder : DslBuilder<RuntimeHandler> {
        private var features: RuntimeHandlerFeatures? = null
        var name: String? = null

        fun features(scope: RuntimeHandlerFeatures.Builder.() -> Unit) {
            features = RuntimeHandlerFeatures.Builder().apply(scope).build()
        }

        override fun build(): RuntimeHandler {
            return RuntimeHandler(
                features = features,
                name = name
            )
        }
    }

    class Group : BuilderGroup<RuntimeHandler, Builder>(Builder()) {
        fun handlers(): List<RuntimeHandler>? = items()

        fun addRuntimeHandler(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}