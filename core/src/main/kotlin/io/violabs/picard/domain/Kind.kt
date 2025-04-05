package io.violabs.picard.domain

enum class Kind : K8sEnum {
    CONFIG_MAP,
    CONFIG_MAP_LIST,
    CSI_DRIVER,
    CSI_DRIVER_LIST,
    CSI_NODE,
    CSI_NODE_LIST,
    CSI_STORAGE_CAPACITY,
    CSI_STORAGE_CAPACITY_LIST,
    DEPLOYMENT,
    JOB,
    KUBELET_CONFIGURATION,
    PERSISTENT_VOLUME_CLAIM,
    PERSISTENT_VOLUME_CLAIM_LIST,
    POD,
    POD_LIST,
    REPLICA_SET,
    SECRET,
    SECRET_LIST,
    SERVICE,
    STORAGE_CLASS,
    STORAGE_CLASS_LIST,
    STORAGE_VERSION_MIGRATION,
    STORAGE_VERSION_MIGRATION_LIST;

    override fun toString(): String = properCase()
}