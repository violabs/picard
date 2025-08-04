package io.violabs.picard.v2.resources.workload.pod

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.condition.Condition
import io.violabs.picard.v2.resources.workload.pod.container.status.ContainerStatus
import io.violabs.picard.v2.resources.workload.pod.host.HostIp
import io.violabs.picard.v2.resources.workload.pod.resource.PodResourceClaimStatus
import java.time.LocalDateTime

/**
 * PodStatus represents information about the status of a pod.
 * Status may trail the actual state of a system, especially if the
 * node that hosts the pod cannot contact the control plane.
 */
@GeneratedDsl(withListGroup = true)
data class PodStatus(
    /**
     * nominatedNodeName is set only when this pod preempts other pods on the node,
     * but it cannot be scheduled right away as preemption victims receive their
     * graceful termination periods. This field does not guarantee that the pod
     * will be scheduled on this node. Scheduler may decide to place the pod
     * elsewhere if other nodes become available sooner. Scheduler may also decide
     * to give the resources on this node to a higher priority pod that is created
     * after preemption. As a result, this field may be different than
     * PodSpec.nodeName when the pod is scheduled.
     */
    val nominatedNodeName: String? = null,
    /**
     * hostIP holds the IP address of the host to which the pod is assigned. Empty
     * if the pod has not started yet. A pod can be assigned to a node that has a
     * problem in kubelet which in turns mean that HostIP will not be updated even
     * if there is a node is assigned to pod
     */
    @JsonProperty("hostIP")
    val hostIp: String? = null,
    /**
     * hostIPs holds the IP addresses allocated to the host. If this field is
     * specified, the first entry must match the hostIP field. This list is empty
     * if the pod has not started yet. A pod can be assigned to a node that has a
     * problem in kubelet which in turns means that HostIPs will not be updated
     * even if there is a node is assigned to this pod.
     */
    @JsonProperty("hostIPs")
    val hostIps: List<HostIp>? = null,
    /**
     * RFC 3339 date and time at which the object was acknowledged by the Kubelet.
     * This is before the Kubelet pulled the container image(s) for the pod.
     */
    val startTime: LocalDateTime? = null,
    /**
     * The phase of a Pod is a simple, high-level summary of where the Pod is in
     * its lifecycle. The conditions array, the reason and message fields, and the
     * individual container status arrays contain more detail about the pod's status.
     * There are five possible phase values:
     *
     * Pending: The pod has been accepted by the Kubernetes system, but one or more
     * of the container images has not been created. This includes time before being
     * scheduled as well as time spent downloading images over the network, which
     * could take a while.
     * Running: The pod has been bound to a node, and all of the containers have been created.
     * At least one container is still running, or is in the process of starting or restarting.
     * Succeeded: All containers in the pod have terminated in success, and will not be restarted.
     * Failed: All containers in the pod have terminated, and at least one container has terminated in failure.
     * The container either exited with non-zero status or was terminated by the system.
     * Unknown: For some reason the state of the pod could not be obtained, typically
     * due to an error in communicating with the host of the pod.
     *
     * More info: https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle#pod-phase
     */
    val phase: Phase? = null,
    /**
     * A human readable message indicating details about why the pod is in this condition.
     */
    val message: String? = null,
    /**
     * A brief CamelCase message indicating details about why the pod is in this state. e.g. 'Evicted'
     */
    val reason: String? = null,
    /**
     * podIP address allocated to the pod. Routable at least within the cluster. Empty if not yet allocated.
     */
    @JsonProperty("podIP")
    val podIp: String? = null,
    /**
     * podIPs holds the IP addresses allocated to the pod. If this field is specified,
     * the 0th entry must match the podIP field. This list is empty if no IPs have been allocated yet.
     */
    @JsonProperty("podIPs")
    val podIps: List<PodIp>? = null,
    /**
     * Current service state of pod.
     * More info: https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle#pod-conditions
     */
    val conditions: List<Condition>? = null,
    /**
     * The Quality of Service (QOS) classification assigned to the pod based on
     * resource requirements See PodQOSClass type for available QOS classes
     * More info: https://kubernetes.io/docs/concepts/workloads/pods/pod-qos/#quality-of-service-classes
     */
    val qosClass: String? = null,
    /**
     * The list of container statuses for init containers.
     */
    val initContainerStatuses: List<ContainerStatus>? = null,
    /**
     * The list of container statuses, one for each container in the pod.
     */
    val containerStatuses: List<ContainerStatus>? = null,
    /**
     * Status for any ephemeral containers that have run in this pod.
     */
    val ephemeralContainerStatuses: List<ContainerStatus>? = null,
    /**
     * Status of resource claims.
     */
    val resourceClaimStatuses: List<PodResourceClaimStatus>? = null,
    /**
     * Status of resources resize desired for pod's containers. It is empty
     * if no resources resize is pending. Any changes to container resources
     * will automatically set this to "Proposed"
     */
    val resize: String? = null,
    /**
     * If set, this represents the .metadata.generation that the pod status was set based upon.
     * This is an alpha field. Enable PodObservedGenerationTracking to be able to use this field.
     */
    val observedGeneration: Long? = null
) {
    enum class Phase {
        Pending,
        Running,
        Succeeded,
        Failed,
        Unknown
    }
}