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
    POD,
    REPLICA_SET,
    SECRET,
    SECRET_LIST,
    SERVICE;

    override fun toString(): String = properCase()
}