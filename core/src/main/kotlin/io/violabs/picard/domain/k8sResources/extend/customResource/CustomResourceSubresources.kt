package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.DSLBuilder

data class CustomResourceSubresources(
    val scale: CustomResourceSubresourceScale? = null,
    val status: CustomResourceSubresourceStatus? = null
) {
    class Builder : DSLBuilder<CustomResourceSubresources> {
        private var scale: CustomResourceSubresourceScale? = null
        private var status: CustomResourceSubresourceStatus? = null

        fun scale(block: CustomResourceSubresourceScale.Builder.() -> Unit) {
            this.scale = CustomResourceSubresourceScale.Builder().apply(block).build()
        }

        fun status(block: CustomResourceSubresourceStatus.Builder.() -> Unit) {
            this.status = CustomResourceSubresourceStatus.Builder().apply(block).build()
        }

        override fun build(): CustomResourceSubresources {
            return CustomResourceSubresources(
                scale = scale,
                status = status
            )
        }
    }
}