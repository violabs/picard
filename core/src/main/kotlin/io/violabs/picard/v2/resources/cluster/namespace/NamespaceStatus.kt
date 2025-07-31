package io.violabs.picard.v2.resources.cluster.namespace

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * NamespaceStatus is information about the current status of a Namespace.
 */
@GeneratedDsl(withListGroup = true)
data class NamespaceStatus(
    /**
     * Patch strategy: merge on key type
     *
     * Map: unique values on key type will be kept during a merge
     *
     * Represents the latest available observations of a namespace's current state.
     */
    val conditions: List<NamespaceCondition>? = null,
    /**
     * Phase is the current lifecycle phase of the namespace. More info: https://kubernetes.io/docs/tasks/administer-cluster/namespaces/
     */
    val phase: String? = null
)