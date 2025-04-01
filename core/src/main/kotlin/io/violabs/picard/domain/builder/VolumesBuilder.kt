package io.violabs.picard.domain.builder

import io.violabs.picard.domain.Volume

class VolumesBuilder : Builder<List<Volume>> {
    private val volumes: MutableList<Volume> = mutableListOf()

    override fun build(): List<Volume> {
        return volumes
    }

    fun volume(scope: VolumeBuilder.() -> Unit) {
        volumes.add(scopedBuild(::VolumeBuilder, scope))
    }

    fun defaultEmptyDirVolume(name: String) {
        volumes.add(Volume(name, "{}"))
    }
}