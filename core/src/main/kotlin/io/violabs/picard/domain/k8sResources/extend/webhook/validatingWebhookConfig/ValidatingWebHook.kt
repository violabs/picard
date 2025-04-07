package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.BaseWebhook

data class ValidatingWebHook(
    val admissionReviewVersions: List<String>,
    val clientConfig: WebhookClientConfig,
    val name: String,
    val sideEffect: String,
    val failurePolicy: String? = null,
    val matchConditions: List<MatchCondition>? = null,
    val matchPolicy: String? = null,
    val namespaceSelector: LabelSelector? = null,
    val objectSelector: LabelSelector? = null,
    val rules: List<RuleWithOperations>? = null,
    val timeoutSeconds: Int? = null
) : BaseWebhook