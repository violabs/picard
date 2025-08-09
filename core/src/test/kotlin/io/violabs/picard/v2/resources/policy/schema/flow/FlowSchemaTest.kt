package io.violabs.picard.v2.resources.policy.schema.flow

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
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
            nonResourceUrls = listOf(PLACEHOLDER),
            verbs = listOf(PLACEHOLDER)
        )

        private val RESOURCE_RULE = ResourcePolicyRule(
            apiGroups = listOf(PLACEHOLDER),
            resources = listOf(PLACEHOLDER),
            verbs = listOf(PLACEHOLDER),
            clusterScope = true,
            namespaces = listOf(PLACEHOLDER)
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
                                        nonResourceUrls(PLACEHOLDER)
                                        verbs(PLACEHOLDER)
                                    }
                                }

                                resourceRules {
                                    resourcePolicyRule {
                                        apiGroups(PLACEHOLDER)
                                        resources(PLACEHOLDER)
                                        verbs(PLACEHOLDER)
                                        clusterScope()
                                        namespaces(PLACEHOLDER)
                                    }
                                }
                            }
                        }
                    }

                    status {
                        conditions {
                            flowSchemaCondition {
                                type = PLACEHOLDER
                                status = BooleanType.True
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
                                status = BooleanType.True,
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