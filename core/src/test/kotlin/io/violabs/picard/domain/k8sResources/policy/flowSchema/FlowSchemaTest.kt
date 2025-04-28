package io.violabs.picard.domain.k8sResources.policy.flowSchema


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.policy.FlowDistinguisherMethod
import io.violabs.picard.domain.k8sResources.policy.PolicyRuleSubject
import io.violabs.picard.domain.k8sResources.policy.PolicyRulesWithSubjects
import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationReference
import io.violabs.picard.domain.k8sResources.policy.rule.NonResourcePolicyRule
import io.violabs.picard.domain.k8sResources.policy.rule.ResourcePolicyRule
import io.violabs.picard.domain.k8sResources.policy.subject.GroupSubject
import io.violabs.picard.domain.k8sResources.policy.subject.ServiceAccountSubject
import io.violabs.picard.domain.k8sResources.policy.subject.UserSubject
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class FlowSchemaTest : SuccessBuildSim<FlowSchema, FlowSchema.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            FlowSchemaTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUBJECT = PolicyRuleSubject(
            kind = PLACEHOLDER,
            group = GroupSubject(PLACEHOLDER),
            serviceAccount = ServiceAccountSubject(
                namespace = PLACEHOLDER,
                name = PLACEHOLDER
            ),
            user = UserSubject(PLACEHOLDER)
        )

        private val NON_RESOURCE_RULE = NonResourcePolicyRule(
            nonResourceURLs = listOf(PLACEHOLDER),
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

        private val SUCCESS_POSSIBILITIES = possibilities<FlowSchema, FlowSchema.Builder> {
            scenario {
                id = "minimum"
                given(FlowSchema.Builder())
                expected = FlowSchema()
            }

            scenario {
                id = "full"
                given(FlowSchema.Builder()) {
                    sharedMetadata()
                    spec {
                        priorityLevelConfiguration(PLACEHOLDER)
                        distinguisherMethod(FlowDistinguisherMethod.Type.ByUser)
                        matchingPrecedence = 1
                        rules {
                            item {
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
                                    rule {
                                        nonResourceURLs(PLACEHOLDER)
                                        verbs(PLACEHOLDER)
                                    }
                                }
                                resourceRules {
                                    rule {
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
                        conditions { sharedCondition() }
                    }
                }
                expected = FlowSchema(
                    metadata = METADATA,
                    spec = FlowSchema.Spec(
                        priorityLevelConfiguration = PriorityLevelConfigurationReference(PLACEHOLDER),
                        distinguisherMethod = FlowDistinguisherMethod(FlowDistinguisherMethod.Type.ByUser.name),
                        matchingPrecedence = 1,
                        rules = listOf(
                            POLICY_RULES_WITH_SUBJECTS
                        )
                    ),
                    status = FlowSchema.Status(
                        conditions = listOf(CONDITION)
                    )
                )
            }
        }
    }
}