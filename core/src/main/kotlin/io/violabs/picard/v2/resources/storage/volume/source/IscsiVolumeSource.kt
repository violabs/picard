package io.violabs.picard.v2.resources.configstorage.volume.source

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LocalObjectReference

/**
 * Represents an ISCSI disk. ISCSI volumes can only be mounted as read/write once. ISCSI volumes
 * support ownership management and SELinux relabeling.
 */
@GeneratedDsl
data class IscsiVolumeSource(
    /**
     * iqn is the target iSCSI Qualified Name.
     */
    val iqn: String,
    /**
     * lun is the iSCSI target lun number.
     */
    val lun: Int,
    /**
     * targetPortal is iSCSI Target Portal. The Portal is either an IP or ip_addr:port if the port
     * is other than default (typically TCP ports 860 and 3260).
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
     * fsType is the filesystem type to mount. Must be a filesystem type supported by the host
     * operating system. Ex. "ext4", "xfs", "ntfs". Implicitly inferred to be "ext4" if unspecified.
     */
    val fsType: String? = null,
    /**
     * initiatorName is the custom iSCSI Initiator Name. If initiatorName is specified with
     * iscsiInterface simultaneously, new iSCSI interface <target portal>:<volume name> will be
     * created for the connection.
     */
    val initiatorName: String? = null,
    /**
     * iscsiInterface is the interface Name that uses an iSCSI transport. Defaults to 'default' (tcp).
     */
    val iscsiInterface: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * portals is the iSCSI Target Portal List. The portal is either an IP or ip_addr:port if the
     * port is other than default (typically TCP ports 860 and 3260).
     */
    val portals: List<String>? = null,
    /**
     * readOnly defaults to false (read/write). ReadOnly here will force the ReadOnly setting in
     * VolumeMounts.
     */
    val readOnly: Boolean? = null,
    /**
     * secretRef is the CHAP Secret for iSCSI target and initiator authentication
     */
    val secretRef: LocalObjectReference? = null
)