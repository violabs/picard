package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * ResourceAttributes includes the authorization attributes available
 * for resource requests to the Authorizer interface
 */
@GeneratedDsl
data class ResourceAttributes(
    /**
     * fieldSelector describes the limitation on access based on
     * field. It can only limit access, not broaden it.
     *
     * This field is alpha-level. To use this field, you must enable
     * the AuthorizeWithSelectors feature gate (disabled by default).
     */
    val fieldSelector: FieldSelectorAttributes? = null,
    /**
     * resourceAttributes.group (string)
     *
     * Group is the API Group of the Resource. "*" means all.
     */
    val group: String? = null,
    /**
     * resourceAttributes.labelSelector (LabelSelectorAttributes)
     *
     * labelSelector describes the limitation on access based on labels. It can only limit access, not broaden it.
     *
     * This field is alpha-level. To use this field, you must enable the AuthorizeWithSelectors feature gate (disabled by default).
     */
    val labelSelector: LabelSelectorAttributes? = null,
    /**
     * resourceAttributes.name (string)
     *
     * Name is the name of the resource being requested for a "get" or deleted for a "delete". "" (empty) means all.
     */
    val name: String? = null,
    /**
     * resourceAttributes.namespace (string)
     *
     * Namespace is the namespace of the action being requested. Currently, there is no distinction between no namespace and all namespaces "" (empty) is defaulted for LocalSubjectAccessReviews "" (empty) is empty for cluster-scoped resources "" (empty) means "all" for namespace scoped resources from a SubjectAccessReview or SelfSubjectAccessReview
     */
    val namespace: String? = null,
    /**
     * resourceAttributes.resource (string)
     *
     * Resource is one of the existing resource types. "*" means all.
     */
    val resource: String? = null,
    /**
     * resourceAttributes.subresource (string)
     *
     * Subresource is one of the existing resource types. "" means none.
     */
    val subresource: String? = null,
    /**
     * resourceAttributes.verb (string)
     *
     * Verb is a kubernetes resource API verb, like: get, list, watch, create, update, delete, proxy. "*" means all.
     */
    val verb: String? = null,
    /**
     * resourceAttributes.version (string)
     *
     * Version is the API Version of the Resource. "*" means all.
     */
    val version: String? = null
)

