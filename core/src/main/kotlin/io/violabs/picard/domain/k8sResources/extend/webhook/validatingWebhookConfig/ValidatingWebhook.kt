package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.BaseWebhook
import io.violabs.picard.domain.k8sResources.extend.webhook.Webhook

data class ValidatingWebhook(
    override val admissionReviewVersions: List<String>,
    override val clientConfig: WebhookClientConfig,
    override val name: String,
    override val sideEffects: String,
    override val failurePolicy: String? = null,
    override val matchConditions: List<MatchCondition>? = null,
    override val matchPolicy: String? = null,
    override val namespaceSelector: LabelSelector? = null,
    override val objectSelector: LabelSelector? = null,
    override val rules: List<RuleWithOperations>? = null,
    override val timeoutSeconds: Int? = null
) : BaseWebhook, Webhook {
    class Builder : Webhook.Builder<ValidatingWebhook>() {

        override fun build(): ValidatingWebhook {
            return ValidatingWebhook(
                admissionReviewVersions = vRequireNotNull(this::admissionReviewVersions),
                clientConfig = vRequireNotNull(this::clientConfig),
                name = vRequireNotNull(this::name),
                sideEffects = vRequireNotNull(this::sideEffects),
                failurePolicy = failurePolicy,
                matchConditions = matchConditions,
                matchPolicy = matchPolicy,
                namespaceSelector = namespaceSelector,
                objectSelector = objectSelector,
                rules = rules,
                timeoutSeconds = timeoutSeconds
            )
        }
    }

    class Group : Webhook.Group<ValidatingWebhook, Builder>(Builder())
}