package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.domain.LabelSelector
import io.violabs.picard.domain.k8sResources.extend.webhook.MatchCondition
import io.violabs.picard.domain.k8sResources.extend.webhook.RuleWithOperations
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookClientConfig
import io.violabs.picard.domain.BaseWebhook
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

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
) : BaseWebhook {
    class Builder : DSLBuilder<MutatingWebhook> {
        private var admissionReviewVersions: List<String>? = null
        private var clientConfig: WebhookClientConfig? = null
        var name: String? = null
        var sideEffects: String? = null
        var failurePolicy: String? = null
        private var matchConditions: List<MatchCondition>? = null
        var matchPolicy: String? = null
        private var namespaceSelector: LabelSelector? = null
        private var objectSelector: LabelSelector? = null
        var reinvocationPolicy: String? = null
        private var rules: List<RuleWithOperations>? = null
        var timeoutSeconds: Int? = null

        fun admissionReviewVersions(vararg versions: String) {
            admissionReviewVersions = versions.toList()
        }

        fun clientConfig(scope: WebhookClientConfig.Builder.() -> Unit) {
            clientConfig = WebhookClientConfig.Builder().apply(scope).build()
        }

        fun matchConditions(scope: MatchCondition.Group.() -> Unit) {
            matchConditions = MatchCondition.Group().apply(scope).conditions()
        }

        fun namespaceSelector(scope: LabelSelector.Builder.() -> Unit) {
            namespaceSelector = LabelSelector.Builder().apply(scope).build()
        }

        fun objectSelector(scope: LabelSelector.Builder.() -> Unit) {
            objectSelector = LabelSelector.Builder().apply(scope).build()
        }

        fun rules(scope: RuleWithOperations.Group.() -> Unit) {
            rules = RuleWithOperations.Group().apply(scope).rules()
        }

        override fun build(): MutatingWebhook {
            return MutatingWebhook(
                admissionReviewVersions = requireNotNull(admissionReviewVersions),
                clientConfig = requireNotNull(clientConfig),
                name = requireNotNull(name),
                sideEffects = requireNotNull(sideEffects),
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

    class Group : BuilderGroup<MutatingWebhook, Builder>(Builder()) {
        fun webhooks(): List<MutatingWebhook>? = items()

        fun webhook(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}