package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.domain.DSLBuilder

data class CustomResourceSubresourceStatus(
    val content: Any? = null
) {
    class Builder : DSLBuilder<CustomResourceSubresourceStatus> {
        var content: Any? = null

        override fun build(): CustomResourceSubresourceStatus {
            return CustomResourceSubresourceStatus(
                content = content
            )
        }
    }
}