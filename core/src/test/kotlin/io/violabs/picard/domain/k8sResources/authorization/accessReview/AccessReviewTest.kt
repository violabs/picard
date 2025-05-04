package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.domain.HTTPVerb
import io.violabs.picard.domain.label.LabelSelectorRequirement
import io.violabs.picard.domain.k8sResources.authorization.*

interface AccessReviewTest {
    fun sharedSubjectAccessReviewSpec(): SubjectAccessReview.Spec {
        return SPEC
    }

    fun SubjectAccessReview.Spec.Builder.sharedSubjectAccessReviewSpec() {
        extra(PLACEHOLDER to listOf(PLACEHOLDER))
        groups(PLACEHOLDER)
        nonResourceAttributes {
            sharedNonResourceAttributes()
        }
        resourceAttributes {
            sharedResourceAttributes()
        }
        uid = PLACEHOLDER
        user = PLACEHOLDER
    }

    fun sharedNonResourceAttributes(): NonResourceAttributes {
        return NON_RESOURCE_ATTRIBUTES
    }

    fun NonResourceAttributes.Builder.sharedNonResourceAttributes() {
        path = PLACEHOLDER
        verb = HTTPVerb.GET
    }

    fun sharedResourceAttributes(): ResourceAttributes {
        return RESOURCE_ATTRIBUTES
    }

    fun ResourceAttributes.Builder.sharedResourceAttributes() {
        fieldSelector {
            rawSelector = PLACEHOLDER
            this.resourceAttributes {
                requirement {
                    key = PLACEHOLDER
                    requirements = PLACEHOLDER
                    values(PLACEHOLDER)
                }
            }
        }
        group = PLACEHOLDER

        labelSelector {
            rawSelector = PLACEHOLDER
            requirements {
                requirement {
                    key = PLACEHOLDER
                    operator = PLACEHOLDER
                    values(PLACEHOLDER)
                }
            }
        }

        name = PLACEHOLDER
        namespace = PLACEHOLDER
        resource = PLACEHOLDER
        subresource = PLACEHOLDER
        verb = K8sVerb.GET
        version = PLACEHOLDER
    }

    fun sharedSubjectAccessReviewStatus(): SubjectAccessReview.Status {
        return STATUS
    }

    fun SubjectAccessReview.Status.Builder.sharedSubjectAccessReviewStatus() {
        allowed()
        denied()
        evaluationError = PLACEHOLDER
        reason = PLACEHOLDER
    }

    companion object {
        private val NON_RESOURCE_ATTRIBUTES = NonResourceAttributes(
            path = PLACEHOLDER,
            verb = HTTPVerb.GET
        )

        private val RESOURCE_ATTRIBUTES = ResourceAttributes(
            fieldSelector = FieldSelectorAttributes(
                rawSelector = PLACEHOLDER,
                resourceAttributes = listOf(
                    FieldSelectorRequirement(
                        key = PLACEHOLDER,
                        requirements = PLACEHOLDER,
                        values = listOf(PLACEHOLDER)
                    )
                )
            ),
            group = PLACEHOLDER,
            labelSelector = LabelSelectorAttributes(
                rawSelector = PLACEHOLDER,
                requirements = listOf(
                    LabelSelectorRequirement(
                        key = PLACEHOLDER,
                        operator = PLACEHOLDER,
                        values = listOf(PLACEHOLDER)
                    )
                )
            ),
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            resource = PLACEHOLDER,
            subresource = PLACEHOLDER,
            verb = K8sVerb.GET,
            version = PLACEHOLDER
        )

        private val SPEC = SubjectAccessReview.Spec(
            extra = mapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
            groups = listOf(PLACEHOLDER),
            nonResourceAttributes = NON_RESOURCE_ATTRIBUTES,
            resourceAttributes = RESOURCE_ATTRIBUTES,
            uid = PLACEHOLDER,
            user = PLACEHOLDER
        )

        private val STATUS = SubjectAccessReview.Status(
            allowed = true,
            denied = true,
            evaluationError = PLACEHOLDER,
            reason = PLACEHOLDER
        )
    }
}