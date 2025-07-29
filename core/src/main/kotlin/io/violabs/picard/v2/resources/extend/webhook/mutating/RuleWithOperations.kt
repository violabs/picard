package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * RuleWithOperations is a tuple of Operations and Resources. It is recommended to make sure
 * that all the tuple expansions are valid.
 */
@GeneratedDsl(withListGroup = true)
data class RuleWithOperations(
    /**
     * APIGroups is the API groups the resources belong to. '*' is all groups.
     * If '*' is present, the length of the slice must be one. Required.
     */
    val apiGroups: List<String>,
    /**
     * APIVersions is the API versions the resources belong to. '*' is all versions.
     * If '*' is present, the length of the slice must be one. Required.
     */
    val apiVersions: List<String>,
    /**
     * Operations is the operations the admission hook cares about - CREATE, UPDATE, DELETE, CONNECT
     * or * for all of those operations and any future admission operations that are added.
     * If '*' is present, the length of the slice must be one. Required.
     */
    val operations: List<String>,
    /**
     * Resources is a list of resources this rule applies to.
     */
    val resources: List<String>,
    /**
     * scope specifies the scope of this rule. Valid values are "Cluster", "Namespaced", and "*"
     * "Cluster" means that only cluster-scoped resources will match this rule.
     * Namespace API objects are cluster-scoped. "Namespaced" means that only namespaced
     * resources will match this rule. "*" means that there are no scope restrictions.
     * Subresources match the scope of their parent resource. Default is "*".
     */
    val scope: String? = null
)