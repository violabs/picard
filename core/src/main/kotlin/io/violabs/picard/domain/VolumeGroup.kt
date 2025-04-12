package io.violabs.picard.domain

class VolumeGroup  {
    private val volumes = mutableListOf<Volume>()

    fun volumes(): MutableList<Volume> {
        return volumes
    }

    fun volume(scope: Volume.Builder.() -> Unit) {
        volumes.add(Volume.Builder().apply(scope).build())
    }
}