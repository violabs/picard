package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.workload.pod.LifecycleHandler

data class Lifecycle(
    val postStart: LifecycleHandler? = null,
    val preStop: LifecycleHandler? = null,
    ) {
        class Builder : DslBuilder<Lifecycle> {
            var postStart: LifecycleHandler? = null
            var preStop: LifecycleHandler? = null
            override fun build(): Lifecycle {
                return Lifecycle(
                    postStart,
                    preStop
                )
            }
        }
    }