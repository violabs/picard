package io.violabs.picard.v2.resources.policy.network

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

/**
 * NetworkPolicyPeer describes a peer to allow traffic to/from. Only certain combinations of fields are allowed
 */
@GeneratedDsl(withListGroup = true)
data class NetworkPolicyPeer(
    /**
     * ipBlock defines policy on a particular IPBlock. If this field is set then neither of the other fields can be.
     */
    val ipBlock: IPBlock? = null,
    /**
     * namespaceSelector selects namespaces using cluster-scoped labels. This field follows standard label selector semantics;
     * if present but empty, it selects all namespaces.
     *
     * If podSelector is also set, then the NetworkPolicyPeer as a whole selects the pods matching podSelector
     * in the namespaces selected by namespaceSelector. Otherwise it selects all pods in the namespaces
     * selected by namespaceSelector.
     */
    val namespaceSelector: LabelSelector? = null,
    /**
     * podSelector is a label selector which selects pods. This field follows standard label selector semantics;
     * if present but empty, it selects all pods.
     *
     * If namespaceSelector is also set, then the NetworkPolicyPeer as a whole selects the pods matching podSelector
     * in the Namespaces selected by NamespaceSelector. Otherwise it selects the pods matching podSelector
     * in the policy's own namespace.
     */
    val podSelector: LabelSelector? = null
)