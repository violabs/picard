package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.common.DSLBuilder

data class Lifecycle(
    val postStart: LifecycleHandler? = null,
    val preStop: LifecycleHandler? = null,
    ) {
        class Builder : DSLBuilder<Lifecycle> {
            private var postStart: LifecycleHandler? = null
            private var preStop: LifecycleHandler? = null

            fun postStart(scope: LifecycleHandler.Builder.() -> Unit) {
                postStart = LifecycleHandler.Builder().apply(scope).build()
            }

            fun preStop(scope: LifecycleHandler.Builder.() -> Unit) {
                preStop = LifecycleHandler.Builder().apply(scope).build()
            }

            override fun build(): Lifecycle {
                return Lifecycle(
                    postStart,
                    preStop
                )
            }
        }
    }