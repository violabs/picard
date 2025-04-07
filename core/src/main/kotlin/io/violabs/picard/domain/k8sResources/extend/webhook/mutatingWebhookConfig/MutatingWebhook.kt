package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.BaseWebhook

data class MutatingWebhook(
    val admissionReviewVersions: List<String>,
    val clientConfig: WebhookClientConfig,
    val name: String,
    val sideEffects: String,
    val failurePolicy: String? = null,
    val matchConditions: List<MatchCondition>? = null,
    val matchPolicy: String? = null,
    val namespaceSelector: LabelSelector? = null,
    val objectSelector: LabelSelector? = null,
    val reinvocationPolicy: String? = null,
    val rules: List<RuleWithOperations>? = null,
    val timeoutSeconds: Int? = null
) : BaseWebhook