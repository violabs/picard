package io.violabs.picard.v2.resources.cluster.node

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NodeSpec describes the attributes that a node is created with.
 */
@GeneratedDsl(withListGroup = true)
data class NodeSpec(
    /**
     * Deprecated: Previously used to specify the source of the node's
     * configuration for the DynamicKubeletConfig feature. This feature is removed.
     */
    val configSource: NodeConfigSource? = null,
    /**
     * Deprecated. Not all kubelets will set this field. Remove field after
     * 1.13. see: https://issues.k8s.io/61966
     */
    @JsonProperty("externalID")
    val externalId: String? = null,
    /**
     * PodCIDR represents the pod IP range assigned to the node.
     */
    @JsonProperty("podCIDR")
    val podCidr: String? = null,
    /**
     * Set: unique values will be kept during a merge
     *
     * podCIDRs represents the IP ranges assigned to the node for usage by
     * Pods on that node. If this field is specified, the 0th entry must match
     * the podCIDR field. It may contain at most 1 value for each of IPv4 and IPv6.
     */
    @JsonProperty("podCIDRs")
    val podCidrs: List<String>? = null,
    /**
     * ID of the node assigned by the cloud provider in the
     * format: <ProviderName>://<ProviderSpecificNodeID>
     */
    @JsonProperty("providerID")
    val providerId: String? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * If specified, the node's taints.
     */
    val taints: List<Taint>? = null,
    /**
     * Unschedulable controls node schedulability of new pods. By default, node is
     * schedulable. More info: https://kubernetes.io/docs/concepts/nodes/node/#manual-node-administration
     */
    val unschedulable: Boolean? = null
)