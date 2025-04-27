package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.LabelSelector

interface Webhook {
    val admissionReviewVersions: List<String>
    val clientConfig: WebhookClientConfig
    val name: String
    val sideEffects: String
    val failurePolicy: String?
    val matchConditions: List<MatchCondition>?
    val matchPolicy: String?
    val namespaceSelector: LabelSelector?
    val objectSelector: LabelSelector?
    val rules: List<RuleWithOperations>?
    val timeoutSeconds: Int?

    abstract class Builder<T : Webhook> : DSLBuilder<T> {
        protected var admissionReviewVersions: List<String>? = null
        protected var clientConfig: WebhookClientConfig? = null
        var name: String? = null
        var sideEffects: String? = null
        var failurePolicy: String? = null
        protected var matchConditions: List<MatchCondition>? = null
        var matchPolicy: String? = null
        protected var namespaceSelector: LabelSelector? = null
        protected var objectSelector: LabelSelector? = null
        protected var rules: List<RuleWithOperations>? = null
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
    }

    abstract class Group<T : Webhook, B : Builder<T>>(builder: B) : BuilderGroup<T, B>(builder) {
        fun webhooks(): List<T>? = items()

        fun webhook(scope: B.() -> Unit) {
            add(scope)
        }
    }
}