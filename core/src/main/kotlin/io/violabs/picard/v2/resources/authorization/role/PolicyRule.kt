package io.violabs.picard.v2.resources.authorization.role

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * PolicyRule holds information that describes a policy rule, but
 * does not contain information about who the rule applies to or
 * which namespace the rule applies to.
 */
@GeneratedDsl(withListGroup = true)
data class PolicyRule(
    /**
     * rules.verbs ([]string), required
     *
     * Atomic: will be replaced during a merge
     *
     * Verbs is a list of Verbs that apply to ALL the
     * ResourceKinds contained in this rule. '*' represents all verbs.
     */
    val verbs: List<String>,
    /**
     * Atomic: will be replaced during a merge
     *
     * APIGroups is the name of the APIGroup that contains the resources.
     * If multiple API groups are specified, any action requested against
     * one of the enumerated resources in any API group will be allowed.
     * "" represents the core API group and "*" represents all API groups.
     */
    val apiGroups: List<String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * Resources is a list of resources this rule applies to. '*' represents all resources.
     */
    val resources: List<String>? = null,
    /**
     * Atomic: will be replaced during a merge
     *
     * ResourceNames is an optional white list of names that the rule
     * applies to. An empty set means that everything is allowed.
     */
    val resourceNames: List<String>? = null,
    /**
     * rules.nonResourceURLs ([]string)
     *
     * Atomic: will be replaced during a merge
     *
     * NonResourceURLs is a set of partial urls that a user should have
     * access to. *s are allowed, but only as the full, final step in the
     * path Since non-resource URLs are not namespaced, this field is only
     * applicable for ClusterRoles referenced from a ClusterRoleBinding.
     * Rules can either apply to API resources (such as "pods" or "secrets")
     * or non-resource URL paths (such as "/api"), but not both.
     */
    val nonResourceURLs: List<String>? = null
)

