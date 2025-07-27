package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl(withListGroup = true)
data class ResourceRule(
    /**
     * Atomic: will be replaced during a merge
     *
     * Verb is a list of kubernetes resource API verbs,
     * like: get, list, watch, create, update, delete, proxy. "*" means all.
     */
    val verbs: List<String>,
    /**
     * Atomic: will be replaced during a merge
     *
     * APIGroups is the name of the APIGroup that contains the resources.
     * If multiple API groups are specified, any action requested against
     * one of the enumerated resources in any API group will be allowed. "*" means all.
     */
    val apiGroups: List<String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * ResourceNames is an optional white list of names that the rule applies
     * to. An empty set means that everything is allowed. "*" means all.
     */
    val resourceNames: List<String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * Resources is a list of resources this rule applies to. "" means all
     * in the specified apiGroups. "/foo" represents the subresource 'foo'
     * for all resources in the specified apiGroups.
     */
    val resources: List<String>? = null
)

