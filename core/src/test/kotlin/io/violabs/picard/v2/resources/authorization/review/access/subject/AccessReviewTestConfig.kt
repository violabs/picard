package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.v2.common.LabelSelectorRequirement
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.FieldSelectorAttributes
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.FieldSelectorRequirement
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.LabelSelectorAttributes
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.NonResourceAttributes
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.NonResourceAttributesDslBuilder
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.ResourceAttributes
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.ResourceAttributesDslBuilder

interface AccessReviewTestConfig {
    fun sharedSubjectAccessReviewSpec(): SubjectAccessReviewSpec {
        return SPEC
    }

    fun SubjectAccessReviewSpecDslBuilder.sharedSubjectAccessReviewSpec() {
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

    fun NonResourceAttributesDslBuilder.sharedNonResourceAttributes() {
        path = PLACEHOLDER
        verb = "GET"
    }

    fun sharedResourceAttributes(): ResourceAttributes {
        return RESOURCE_ATTRIBUTES
    }

    fun ResourceAttributesDslBuilder.sharedResourceAttributes() {
        fieldSelector {
            rawSelector = PLACEHOLDER
            requirements {
                fieldSelectorRequirement {
                    key = PLACEHOLDER
                    operator = FieldSelectorRequirement.Operator.In
                    values(PLACEHOLDER)
                }
            }
        }
        group = PLACEHOLDER

        labelSelector {
            rawSelector = PLACEHOLDER
            requirements {
                labelSelectorRequirement {
                    key = PLACEHOLDER
                    operator = LabelSelectorRequirement.Operator.In
                    values(PLACEHOLDER)
                }
            }
        }

        name = PLACEHOLDER
        namespace = PLACEHOLDER
        resource = PLACEHOLDER
        subresource = PLACEHOLDER
        verb = "GET"
        version = PLACEHOLDER
    }

    fun sharedSubjectAccessReviewStatus(): SubjectAccessReviewStatus {
        return STATUS
    }

    fun SubjectAccessReviewStatusDslBuilder.sharedSubjectAccessReviewStatus() {
        allowed()
        denied()
        evaluationError = PLACEHOLDER
        reason = PLACEHOLDER
    }

    companion object {
        private val NON_RESOURCE_ATTRIBUTES = NonResourceAttributes(
            path = PLACEHOLDER,
            verb = "GET"
        )

        private val RESOURCE_ATTRIBUTES = ResourceAttributes(
            fieldSelector = FieldSelectorAttributes(
                rawSelector = PLACEHOLDER,
                requirements = listOf(
                    FieldSelectorRequirement(
                        key = PLACEHOLDER,
                        operator = FieldSelectorRequirement.Operator.In,
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
                        operator = LabelSelectorRequirement.Operator.In,
                        values = listOf(PLACEHOLDER)
                    )
                )
            ),
            name = PLACEHOLDER,
            namespace = PLACEHOLDER,
            resource = PLACEHOLDER,
            subresource = PLACEHOLDER,
            verb = "GET",
            version = PLACEHOLDER
        )

        private val SPEC = SubjectAccessReviewSpec(
            extra = mapOf(PLACEHOLDER to listOf(PLACEHOLDER)),
            groups = listOf(PLACEHOLDER),
            nonResourceAttributes = NON_RESOURCE_ATTRIBUTES,
            resourceAttributes = RESOURCE_ATTRIBUTES,
            uid = PLACEHOLDER,
            user = PLACEHOLDER
        )

        private val STATUS = SubjectAccessReviewStatus(
            allowed = true,
            denied = true,
            evaluationError = PLACEHOLDER,
            reason = PLACEHOLDER
        )
    }
}