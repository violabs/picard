package io.violabs.picard.v2.resources.cluster.runtimeclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * RuntimeClass defines a class of container runtime supported in the cluster.
 * The RuntimeClass is used to determine which container runtime is used to run all containers in a pod.
 * RuntimeClasses are manually defined by a user or cluster provisioner, and referenced in the PodSpec.
 * The Kubelet is responsible for resolving the RuntimeClassName reference before running the pod.
 * For more details, see https://kubernetes.io/docs/concepts/containers/runtime-class/
 *
 * apiVersion: node.k8s.io/v1
 *
 * import "k8s.io/api/node/v1"
 */
@GeneratedDsl(withListGroup = true)
data class RuntimeClassV2(
    @DefaultValue(
        "KAPIVersion.NodeV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.NodeV1,
    override val metadata: ObjectMeta? = null,
    /**
     * handler specifies the underlying runtime and configuration that the CRI
     * implementation will use to handle pods of this class.
     * The possible values are specific to the node & CRI configuration.
     * It is assumed that all handlers are available on every node, and handlers of the
     * same name are equivalent on every node.
     * For example, a handler called "runc" might specify that the runc OCI runtime
     * (using native Linux containers) will be used to run the containers in a pod.
     * The Handler must be lowercase, conform to the DNS Label (RFC 1123) requirements, and is immutable.
     */
    val handler: String,
    /**
     * overhead represents the resource overhead associated with running a pod for a given RuntimeClass.
     * For more details, see https://kubernetes.io/docs/concepts/scheduling-eviction/pod-overhead/
     */
    val overhead: Overhead? = null,
    /**
     * scheduling holds the scheduling constraints to ensure that pods running with this
     * RuntimeClass are scheduled to nodes that support it.
     * If scheduling is nil, this RuntimeClass is assumed to be supported by all nodes.
     */
    val scheduling: Scheduling? = null
) : ClusterResource<RuntimeClassV2.Version, ObjectMeta> {
    interface Version : APIVersion
}