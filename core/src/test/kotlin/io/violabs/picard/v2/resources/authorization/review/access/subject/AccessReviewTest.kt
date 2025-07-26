package io.violabs.picard.v2.resources.authorization.review.access.subject

import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.v2.common.LabelSelectorRequirement

interface AccessReviewTest {
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
                    operator = PLACEHOLDER
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
                        operator = PLACEHOLDER,
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