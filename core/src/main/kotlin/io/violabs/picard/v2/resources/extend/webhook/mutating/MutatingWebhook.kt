package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.common.LabelSelector

/**
 * MutatingWebhook describes an admission webhook and the resources and operations it applies to.
 */
@GeneratedDsl(withListGroup = true)
data class MutatingWebhook(
    /**
     * AdmissionReviewVersions is an ordered list of preferred AdmissionReview versions
     * the Webhook expects. API server will try to use first version in the list which it supports.
     * If none of the versions specified in this list supported by API server, validation will fail
     * for this object. If a persisted webhook configuration specifies allowed versions and does not
     * include any versions known to the API Server, calls to the webhook will fail and be subject
     * to the failure policy.
     */
    val admissionReviewVersions: List<String>,
    /**
     * ClientConfig defines how to communicate with the hook. Required
     */
    val clientConfig: WebhookClientConfig,
    /**
     * The name of the admission webhook. Name should be fully qualified, e.g.,
     * imagepolicy.kubernetes.io, where "imagepolicy" is the name of the webhook,
     * and kubernetes.io is the name of the organization. Required.
     */
    val name: String,
    /**
     * SideEffects states whether this webhook has side effects. Acceptable values are:
     * None, NoneOnDryRun (webhooks created via v1beta1 may also specify Some or Unknown).
     * Webhooks with side effects MUST implement a reconciliation system, since a request
     * may be rejected by a future step in the admission chain and the side effects therefore
     * need to be undone. Requests with the dryRun attribute will be auto-rejected if they
     * match a webhook with sideEffects == Unknown or Some.
     */
    val sideEffects: String,
    /**
     * FailurePolicy defines how unrecognized errors from the admission endpoint are handled -
     * allowed values are Ignore or Fail. Defaults to Fail.
     */
    val failurePolicy: String? = null,
    /**
     * MatchConditions is a list of conditions that must be met for a request to be sent to this webhook.
     * Match conditions filter requests that have already been matched by the rules, namespaceSelector,
     * and objectSelector. An empty list of matchConditions matches all requests.
     * There are a maximum of 64 match conditions allowed.
     */
    val matchConditions: List<MatchCondition>? = null,
    /**
     * matchPolicy defines how the "rules" list is used to match incoming requests.
     * Allowed values are "Exact" or "Equivalent".
     */
    val matchPolicy: String? = null,
    /**
     * NamespaceSelector decides whether to run the webhook on an object based on whether
     * the namespace for that object matches the selector.
     */
    val namespaceSelector: LabelSelector? = null,
    /**
     * ObjectSelector decides whether to run the webhook based on if the object has matching labels.
     * objectSelector is evaluated against both the oldObject and newObject that would be sent to
     * the webhook, and is considered to match if either object matches the selector.
     */
    val objectSelector: LabelSelector? = null,
    /**
     * reinvocationPolicy indicates whether this webhook should be called multiple times as part
     * of a single admission evaluation. Allowed values are "Never" and "IfNeeded".
     */
    val reinvocationPolicy: String? = null,
    /**
     * Rules describes what operations on what resources/subresources the webhook cares about.
     * The webhook cares about an operation if it matches any Rule.
     */
    val rules: List<RuleWithOperations>? = null,
    /**
     * TimeoutSeconds specifies the timeout for this webhook. After the timeout passes,
     * the webhook call will be ignored or the API call will fail based on the failure policy.
     * The timeout value must be between 1 and 30 seconds. Default to 10 seconds.
     */
    val timeoutSeconds: Int? = null
)