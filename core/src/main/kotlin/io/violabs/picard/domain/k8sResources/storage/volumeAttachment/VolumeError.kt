package io.violabs.picard.domain.k8sResources.storage.volumeAttachment

import io.violabs.picard.common.DslBuilder
import java.time.LocalDateTime

data class VolumeError(
    val errorCode: Int? = null,
    val message: String? = null,
    val time: LocalDateTime? = null
) {
    class Builder : DslBuilder<VolumeError> {
        var errorCode: Int? = null
        var message: String? = null
        var time: LocalDateTime? = null

        override fun build(): VolumeError {
            return VolumeError(
                errorCode = errorCode,
                message = message,
                time = time
            )
        }
    }
}