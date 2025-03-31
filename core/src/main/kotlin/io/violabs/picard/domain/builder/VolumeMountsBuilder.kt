package io.violabs.picard.domain.builder

import io.violabs.picard.domain.VolumeMount

class VolumeMountsBuilder : Builder<List<VolumeMount>> {
    private val volumeMounts: MutableList<VolumeMount> = mutableListOf()

    override fun build(): List<VolumeMount> = volumeMounts

    fun volumeMount(scope: VolumeMountBuilder.() -> Unit) {
        volumeMounts.add(scopedBuild(::VolumeMountBuilder, scope))
    }

    /**
     * For shared volume mount objects
     */
    fun volumeMount(volumeMount: VolumeMount) {
        volumeMounts.add(volumeMount)
    }
}