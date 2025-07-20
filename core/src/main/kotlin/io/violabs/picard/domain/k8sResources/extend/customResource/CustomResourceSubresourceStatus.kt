package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.DslBuilder

data class CustomResourceSubresourceStatus(
    val content: Any? = null
) {
    class Builder : DslBuilder<CustomResourceSubresourceStatus> {
        var content: Any? = null

        override fun build(): CustomResourceSubresourceStatus {
            return CustomResourceSubresourceStatus(
                content = content
            )
        }
    }
}