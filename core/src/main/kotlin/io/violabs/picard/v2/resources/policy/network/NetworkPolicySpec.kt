package io.violabs.picard.v2.resources.policy.network

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

/**
 * NetworkPolicySpec provides the specification of a NetworkPolicy
 */
@GeneratedDsl
data class NetworkPolicySpec(
    /**
     * podSelector selects the pods to which this NetworkPolicy object applies.
     * The array of ingress rules is applied to any pods selected by this field.
     * Multiple network policies can select the same set of pods. In this case,
     * the ingress rules for each are combined additively. This field is NOT optional
     * and follows standard label selector semantics. An empty podSelector matches all pods in this namespace.
     */
    val podSelector: LabelSelector,
    /**
     * policyTypes is a list of rule types that the NetworkPolicy relates to.
     * Valid options are ["Ingress"], ["Egress"], or ["Ingress", "Egress"].
     * If this field is not specified, it will default based on the existence of ingress or egress rules;
     * policies that contain an egress section are assumed to affect egress, and all policies
     * (whether or not they contain an ingress section) are assumed to affect ingress.
     */
    val policyTypes: List<String>? = null,
    /**
     * ingress is a list of ingress rules to be applied to the selected pods.
     * Traffic is allowed to a pod if there are no NetworkPolicies selecting the pod
     * (and cluster policy otherwise allows the traffic), OR if the traffic source is the pod's local node,
     * OR if the traffic matches at least one ingress rule across all of the NetworkPolicy objects
     * whose podSelector matches the pod. If this field is empty then this NetworkPolicy does not
     * allow any traffic (and serves solely to ensure that the pods it selects are isolated by default)
     */
    val ingress: List<NetworkPolicyIngressRule>? = null,
    /**
     * egress is a list of egress rules to be applied to the selected pods.
     * Outgoing traffic is allowed if there are no NetworkPolicies selecting the pod
     * (and cluster policy otherwise allows the traffic), OR if the traffic matches at least one egress rule
     * across all of the NetworkPolicy objects whose podSelector matches the pod.
     * If this field is empty then this NetworkPolicy limits all outgoing traffic
     * (and serves solely to ensure that the pods it selects are isolated by default).
     */
    val egress: List<NetworkPolicyEgressRule>? = null
)