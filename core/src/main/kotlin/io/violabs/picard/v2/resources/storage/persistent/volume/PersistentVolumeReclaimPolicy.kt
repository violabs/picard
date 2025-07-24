package io.violabs.picard.v2.resources.storage.persistent.volume

enum class PersistentVolumeReclaimPolicy {
    Retain,
    Delete,
    @Deprecated("https://kubernetes.io/docs/concepts/storage/persistent-volumes#reclaiming")
    Recycle
}