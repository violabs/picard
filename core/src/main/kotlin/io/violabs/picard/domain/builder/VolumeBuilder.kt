package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Volume

class VolumeBuilder : Builder<Volume> {
    var name: String? = null
    var emptyDir: String? = null

    override fun build(): Volume {
        return Volume(
            name = requireNotNull(name) { "Please provide a name" },
            emptyDir = emptyDir ?: "{}"
        )
    }
}