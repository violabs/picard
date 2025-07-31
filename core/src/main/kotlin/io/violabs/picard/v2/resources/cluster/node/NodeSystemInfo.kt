package io.violabs.picard.v2.resources.cluster.node

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeSystemInfo is a set of ids/uuids to uniquely identify the node.
 */
@GeneratedDsl
data class NodeSystemInfo(
    /**
     * The Architecture reported by the node
     */
    val architecture: String,
    /**
     * Boot ID reported by the node.
     */
    @JsonProperty("bootID")
    val bootId: String,
    /**
     * ContainerRuntime Version reported by the node through runtime remote API (e.g. containerd://1.4.2).
     */
    val containerRuntimeVersion: String,
    /**
     * Kernel Version reported by the node from 'uname -r' (e.g. 3.16.0-0.bpo.4-amd64).
     */
    val kernelVersion: String,
    /**
     * Deprecated: KubeProxy Version reported by the node.
     */
    val kubeProxyVersion: String,
    /**
     * Kubelet Version reported by the node.
     */
    val kubeletVersion: String,
    /**
     * MachineID reported by the node. For unique machine identification in the cluster
     * this field is preferred. Learn more from man(5)
     * machine-id: http://man7.org/linux/man-pages/man5/machine-id.5.html
     */
    @JsonProperty("machineID")
    val machineId: String,
    /**
     * The Operating System reported by the node
     */
    val operatingSystem: String,
    /**
     * OS Image reported by the node from /etc/os-release (e.g. Debian GNU/Linux 7 (wheezy)).
     */
    val osImage: String,
    /**
     * SystemUUID reported by the node. For unique machine identification MachineID
     * is preferred. This field is specific to Red Hat
     * hosts https://access.redhat.com/documentation/en-us/red_hat_subscription_management/1/html/rhsm/uuid
     */
    @JsonProperty("systemUUID")
    val systemUuid: String,
    /**
     * Swap Info reported by the node.
     */
    val swap: NodeSwapStatus? = null
)