package io.violabs.picard.v2.resources.policy.schema.rule

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 *  NonResourcePolicyRule is a predicate that matches non-resource requests
 *  according to their verb and the target non-resource URL. A
 *  NonResourcePolicyRule matches a request if and only if both (a) at least
 *  one member of verbs matches the request and (b) at least one member of
 *  nonResourceURLs matches the request.
 */
@GeneratedDsl(withListGroup = true)
data class NonResourcePolicyRule(
    /**
     * Set: unique values will be kept during a merge
     *
     * nonResourceURLs is a set of url prefixes that a user should have
     * access to and may not be empty. For example:
     *      "/healthz" is legal
     *      "/hea*" is illegal
     *      "/hea" is legal but matches nothing
     *      "/hea/\*" also matches nothing
     *      "/healthz/" matches all per-component health checks. "" matches all
     * non-resource urls. if it is present, it must be the only entry. Required.
     * rules.nonResourceRules.verbs ([]string), required
     *
     * Set: unique values will be kept during a merge
     *
     * verbs is a list of matching verbs and may not be empty. "*" matches all verbs.
     * If it is present, it must be the only entry. Required.
     */
    @JsonProperty("nonResourceURLs")
    val nonResourceUrls: Set<String>,
    /**
     * Set: unique values will be kept during a merge
     *
     * verbs is a list of matching verbs and may not be empty. "*" matches all verbs.
     * If it is present, it must be the only entry. Required.
     */
    val verbs: Set<String>
)

