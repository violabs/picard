package io.violabs.picard.domain

import io.violabs.picard.common.vRequireNotNull

data class Volume(
    val name: String,
    val emptyDir: String? = null
) {
    class Builder : DSLBuilder<Volume> {
        var name: String? = null
        var emptyDir: String? = null

        override fun build(): Volume  {
            return Volume(
                name = vRequireNotNull(this::name),
                emptyDir = emptyDir
            )
        }
    }
}