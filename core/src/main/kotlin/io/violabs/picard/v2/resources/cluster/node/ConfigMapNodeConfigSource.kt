package io.violabs.picard.v2.resources.cluster.node

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ConfigMapNodeConfigSource contains the information to reference a ConfigMap as a config source for the Node. 
 * This API is deprecated since 1.22: https://git.k8s.io/enhancements/keps/sig-node/281-dynamic-kubelet-configuration
 */
@GeneratedDsl
data class ConfigMapNodeConfigSource(
    /**
     * KubeletConfigKey declares which key of the referenced ConfigMap corresponds to the
     * KubeletConfiguration structure This field is required in all cases.
     */
    val kubeletConfigKey: String,
    /**
     * Name is the metadata.name of the referenced ConfigMap. This field is required in all cases.
     */
    val name: String,
    /**
     * Namespace is the metadata.namespace of the referenced ConfigMap. This field is required in all cases.
     */
    val namespace: String,
    /**
     * ResourceVersion is the metadata.ResourceVersion of the referenced ConfigMap. This
     * field is forbidden in Node.Spec, and required in Node.Status.
     */
    val resourceVersion: String? = null,
    /**
     * UID is the metadata.UID of the referenced ConfigMap. This field is forbidden in
     * Node.Spec, and required in Node.Status.
     */
    val uid: String? = null
)