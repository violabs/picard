package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class CustomResourceSubresourceScale(
    val specReplicasPath: String,
    val statusReplicasPath: String,
    val labelSelectorPath: String? = null
) {
    class Builder : DslBuilder<CustomResourceSubresourceScale> {
        var specReplicasPath: String? = null
        var statusReplicasPath: String? = null
        var labelSelectorPath: String? = null

        override fun build(): CustomResourceSubresourceScale {
            return CustomResourceSubresourceScale(
                specReplicasPath = vRequireNotNull(this::specReplicasPath),
                statusReplicasPath = vRequireNotNull(this::statusReplicasPath),
                labelSelectorPath = labelSelectorPath
            )
        }
    }
}