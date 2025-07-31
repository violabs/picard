package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.v2.resources.cluster.node.daemon.NodeDaemonEndpoints
import io.violabs.picard.v2.resources.cluster.node.runtime.NodeRuntimeHandler

/**
 * NodeStatus is information about the current status of a node.
 */
@GeneratedDsl(withListGroup = true)
data class NodeStatus(
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * List of addresses reachable to the node. Queried from cloud provider,
     * if available. More info: https://kubernetes.io/docs/reference/node/node-status/#addresses
     * Note: This field is declared as mergeable, but the merge key is not sufficiently unique,
     * which can cause data corruption when it is merged. Callers should instead use a full-replacement
     * patch. See https://pr.k8s.io/79391 for an example. Consumers should assume that addresses
     * can change during the lifetime of a Node. However, there are some exceptions where this may
     * not be possible, such as Pods that inherit a Node's address in its own status or consumers
     * of the downward API (status.hostIP).
     */
    val addresses: List<NodeAddress>? = null,
    /**
     * Allocatable represents the resources of a node that are available for scheduling. Defaults to Capacity.
     */
    val allocatable: Map<String, Quantity>? = null,
    /**
     * Capacity represents the total resources of a node. More info:
     * https://kubernetes.io/docs/reference/node/node-status/#capacity
     */
    val capacity: Map<String, Quantity>? = null,
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * Conditions is an array of current observed node conditions. More
     * info: https://kubernetes.io/docs/reference/node/node-status/#condition
     */
    val conditions: List<NodeCondition>? = null,
    /**
     * Status of the config assigned to the node via the dynamic Kubelet config feature.
     */
    val config: NodeConfigStatus? = null,
    /**
     * Endpoints of daemons running on the Node.
     */
    val daemonEndpoints: NodeDaemonEndpoints? = null,
    /**
     * Features describes the set of features implemented by the CRI implementation.
     */
    val features: NodeFeatures? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * List of container images on this node
     */
    val images: List<ContainerImage>? = null,
    /**
     * Set of ids/uuids to uniquely identify the node.
     * More info: https://kubernetes.io/docs/reference/node/node-status/#info
     */
    val nodeInfo: NodeSystemInfo? = null,
    /**
     * NodePhase is the recently observed lifecycle phase of the node.
     * More info: https://kubernetes.io/docs/concepts/nodes/node/#phase
     * The field is never populated, and now is deprecated.
     */
    val phase: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * The available runtime handlers.
     */
    val runtimeHandlers: List<NodeRuntimeHandler>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * List of volumes that are attached to the node.
     */
    val volumesAttached: List<AttachedVolume>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * List of attachable volumes in use (mounted) by the node.
     */
    val volumesInUse: List<String>? = null
)