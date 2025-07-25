package io.violabs.picard.v2.resources.storage.persistent.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.SecretReference

/**
 * ISCSIPersistentVolumeSource represents an ISCSI disk. ISCSI volumes can only be
 * mounted as read/write once. ISCSI volumes support ownership management and SELinux relabeling.
 */
@GeneratedDsl
data class IscsiPersistentVolumeSource(
    /**
     * iqn is Target iSCSI Qualified Name.
     */
    val iqn: String,
    /**
     * lun is iSCSI Target Lun number.
     */
    val lun: Int,
    /**
     * targetPortal is iSCSI Target Portal. The Portal is either an IP or ip_addr:port if
     * the port is other than default (typically TCP ports 860 and 3260).
     */
    val targetPortal: String,
    /**
     * chapAuthDiscovery defines whether support iSCSI Discovery CHAP authentication
     */
    val chapAuthDiscovery: Boolean? = null,
    /**
     * chapAuthSession defines whether support iSCSI Session CHAP authentication
     */
    val chapAuthSession: Boolean? = null,
    /**
     * fsType is the filesystem type of the volume that you want to mount. Tip: Ensure
     * that the filesystem type is supported by the host operating system. Examples:
     * "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.
     * More info: https://kubernetes.io/docs/concepts/storage/volumes#iscsi
     */
    val fsType: String? = null,
    /**
     * initiatorName is the custom iSCSI Initiator Name. If initiatorName is specified
     * with iscsiInterface simultaneously, new iSCSI interface <target portal>:<volume name>
     *     will be created for the connection.
     */
    val initiatorName: String? = null,
    /**
     * iscsiInterface is the interface Name that uses an iSCSI transport.
     * Defaults to 'default' (tcp).
     */
    val iscsiInterface: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * portals is the iSCSI Target Portal List. The Portal is either an IP or ip_addr:port
     * if the port is other than default (typically TCP ports 860 and 3260).
     */
    val portals: List<String>? = null,
    /**
     * readOnly here will force the ReadOnly setting in VolumeMounts. Defaults to false.
     */
    val readOnly: Boolean? = null,
    /**
     * secretRef is the CHAP Secret for iSCSI target and initiator authentication
     */
    val secretRef: SecretReference? = null
)