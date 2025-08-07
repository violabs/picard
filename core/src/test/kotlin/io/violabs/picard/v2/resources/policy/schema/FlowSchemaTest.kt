package io.violabs.picard.v2.resources.policy.schema


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.policy.schema.flow.FlowDistinguisherMethod
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaCondition
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaSpec
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaStatus
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchema
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaDslBuilder
import io.violabs.picard.v2.resources.policy.schema.flow.PriorityLevelConfigurationReference
import io.violabs.picard.v2.resources.policy.schema.rule.NonResourcePolicyRule
import io.violabs.picard.v2.resources.policy.schema.rule.ResourcePolicyRule
import io.violabs.picard.v2.resources.policy.schema.subject.GroupSubject
import io.violabs.picard.v2.resources.policy.schema.subject.PolicyRulesWithSubjects
import io.violabs.picard.v2.resources.policy.schema.subject.ServiceAccountSubject
import io.violabs.picard.v2.resources.policy.schema.subject.Subject
import io.violabs.picard.v2.resources.policy.schema.subject.UserSubject
import org.junit.jupiter.api.BeforeAll

class FlowSchemaTest : SuccessBuildSim<FlowSchema, FlowSchemaDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUBJECT = Subject(
            kind = PLACEHOLDER,
            group = GroupSubject(PLACEHOLDER),
            serviceAccount = ServiceAccountSubject(
                namespace = PLACEHOLDER,
                name = PLACEHOLDER
            ),
            user = UserSubject(PLACEHOLDER)
        )

        private val NON_RESOURCE_RULE = NonResourcePolicyRule(
            nonResourceUrls = setOf(PLACEHOLDER),
            verbs = setOf(PLACEHOLDER)
        )

        private val RESOURCE_RULE = ResourcePolicyRule(
            apiGroups = setOf(PLACEHOLDER),
            resources = setOf(PLACEHOLDER),
            verbs = setOf(PLACEHOLDER),
            clusterScope = true,
            namespaces = setOf(PLACEHOLDER)
        )

        private val POLICY_RULES_WITH_SUBJECTS = PolicyRulesWithSubjects(
            subjects = listOf(SUBJECT),
            nonResourceRules = listOf(NON_RESOURCE_RULE),
            resourceRules = listOf(RESOURCE_RULE)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<FlowSchema, FlowSchemaDslBuilder> {
            scenario {
                id = "minimum"
                given(FlowSchemaDslBuilder())
                expected = FlowSchema()
            }

            scenario {
                id = "full"
                given(FlowSchemaDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        priorityLevelConfiguration(PLACEHOLDER)
                        distinguisherMethod(FlowDistinguisherMethod.Type.ByUser)
                        matchingPrecedence = 1
                        rules {
                            policyRulesWithSubjects {
                                subjects {
                                    subject {
                                        kind = PLACEHOLDER
                                        group(PLACEHOLDER)
                                        serviceAccount {
                                            namespace = PLACEHOLDER
                                            name = PLACEHOLDER
                                        }
                                        user(PLACEHOLDER)
                                    }
                                }

                                nonResourceRules {
                                    nonResourcePolicyRule {
                                        nonResourceUrls = setOf(PLACEHOLDER)
                                        verbs = setOf(PLACEHOLDER)
                                    }
                                }

                                resourceRules {
                                    resourcePolicyRule {
                                        apiGroups = setOf(PLACEHOLDER)
                                        resources = setOf(PLACEHOLDER)
                                        verbs = setOf(PLACEHOLDER)
                                        clusterScope()
                                        namespaces = setOf(PLACEHOLDER)
                                    }
                                }
                            }
                        }
                    }

                    status {
                        conditions {
                            flowSchemaCondition {
                                type = PLACEHOLDER
                                status = FlowSchemaConditionStatus.True
                                lastTransitionTime = NOW
                                reason = PLACEHOLDER
                                message = PLACEHOLDER
                            }
                        }
                    }
                }

                expected = FlowSchema(
                    metadata = Common.OBJECT_META,
                    spec = FlowSchemaSpec(
                        priorityLevelConfiguration = PriorityLevelConfigurationReference(PLACEHOLDER),
                        distinguisherMethod = FlowDistinguisherMethod(FlowDistinguisherMethod.Type.ByUser),
                        matchingPrecedence = 1,
                        rules = listOf(
                            POLICY_RULES_WITH_SUBJECTS
                        )
                    ),
                    status = FlowSchemaStatus(
                        conditions = listOf(
                            FlowSchemaCondition(
                                type = PLACEHOLDER,
                                status = FlowSchemaConditionStatus.True,
                                lastTransitionTime = NOW,
                                reason = PLACEHOLDER,
                                message = PLACEHOLDER
                            )
                        )
                    )
                )
            }
        }
    }
}