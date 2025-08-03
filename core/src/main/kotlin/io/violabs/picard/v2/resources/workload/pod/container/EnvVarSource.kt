package io.violabs.picard.v2.resources.workload.pod.container

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * EnvVarSource represents a source for the value of an EnvVar.
 */
@GeneratedDsl
data class EnvVarSource(
    /**
     * Selects a key of a ConfigMap.
     */
    val configMapKeyRef: ConfigMapKeySelector? = null,
    /**
     * Selects a field of the pod: supports metadata.name, metadata.namespace, `metadata.labels['<KEY>']`, `metadata.annotations['<KEY>']`, spec.nodeName, spec.serviceAccountName, status.hostIP, status.podIP, status.podIPs.
     */
    val fieldRef: ObjectFieldSelector? = null,
    /**
     * Selects a resource of the container: only resources limits and requests (limits.cpu, limits.memory, limits.ephemeral-storage, requests.cpu, requests.memory and requests.ephemeral-storage) are currently supported.
     */
    val resourceFieldRef: ResourceFieldSelector? = null,
    /**
     * Selects a key of a secret in the pod's namespace
     */
    val secretKeyRef: SecretKeySelector? = null
)