package io.violabs.picard.v2.resources.storage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * Represents an NFS mount that lasts the lifetime of a pod. NFS volumes do not support ownership
 * management or SELinux relabeling.
 */
@GeneratedDsl
data class NfsVolumeSource(
    /**
     * path that is exported by the NFS server. More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs
     */
    val path: String,
    /**
     * server is the hostname or IP address of the NFS server.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs
     */
    val server: String,
    /**
     * readOnly here will force the NFS export to be mounted with read-only permissions. Defaults to false.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#nfs
     */
    val readOnly: Boolean? = null,
)