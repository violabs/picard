package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseWebhook
import io.violabs.picard.domain.label.LabelSelector
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.Webhook
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig

data class MutatingWebhook(
    override val admissionReviewVersions: List<String>,
    override val clientConfig: WebhookClientConfig,
    override val name: String,
    override val sideEffects: String,
    override val failurePolicy: String? = null,
    override val matchConditions: List<MatchCondition>? = null,
    override val matchPolicy: String? = null,
    override val namespaceSelector: LabelSelector? = null,
    override val objectSelector: LabelSelector? = null,
    val reinvocationPolicy: String? = null,
    override val rules: List<RuleWithOperations>? = null,
    override val timeoutSeconds: Int? = null
) : BaseWebhook, Webhook {
    class Builder : Webhook.Builder<MutatingWebhook>() {
        var reinvocationPolicy: String? = null

        override fun build(): MutatingWebhook {
            return MutatingWebhook(
                admissionReviewVersions = vRequireNotNull(this::admissionReviewVersions),
                clientConfig = vRequireNotNull(this::clientConfig),
                name = vRequireNotNull(this::name),
                sideEffects = vRequireNotNull(this::sideEffects),
                failurePolicy = failurePolicy,
                matchConditions = matchConditions,
                matchPolicy = matchPolicy,
                namespaceSelector = namespaceSelector,
                objectSelector = objectSelector,
                reinvocationPolicy = reinvocationPolicy,
                rules = rules,
                timeoutSeconds = timeoutSeconds
            )
        }
    }

    class Group : Webhook.Group<MutatingWebhook, Builder>(Builder())
}