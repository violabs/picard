package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NamespaceSpec describes the attributes on a Namespace.
 */
@GeneratedDsl(withListGroup = true)
data class NamespaceSpec(
    /**
     * Atomic: will be replaced during a merge
     *
     * Finalizers is an opaque list of values that must be empty to permanently remove object from storage. More info: https://kubernetes.io/docs/tasks/administer-cluster/namespaces/
     */
    val finalizers: List<String>? = null
)