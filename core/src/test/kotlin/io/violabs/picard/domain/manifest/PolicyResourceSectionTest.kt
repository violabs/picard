//package io.violabs.picard.domain.manifest
//
//
//import io.violabs.picard.FullBuildSim
//import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchema
//import io.violabs.picard.domain.k8sResources.policy.flowSchema.FlowSchemaList
//import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRange
//import io.violabs.picard.domain.k8sResources.policy.limitRange.LimitRangeList
//import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicy
//import io.violabs.picard.domain.k8sResources.policy.networkPolicy.NetworkPolicyList
//import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudget
//import io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget.PodDisruptionBudgetList
//import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfiguration
//import io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig.PriorityLevelConfigurationList
//import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuota
//import io.violabs.picard.domain.k8sResources.policy.resourceQuota.ResourceQuotaList
//import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicy
//import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.ValidatingAdmissionPolicyList
//import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBinding
//import io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding.ValidatingAdmissionPolicyBindingList
//import io.violabs.picard.possibilities
//import org.junit.jupiter.api.BeforeAll
//
//class PolicyResourceSectionTest : FullBuildSim<PolicyResourceSection, PolicyResourceSectionDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            PolicyResourceSectionTest::class,
//            SUCCESS_POSSIBILITIES,
//            FAILURE_POSSIBILITIES
//        )
//
//        private val SUCCESS_POSSIBILITIES = possibilities<PolicyResourceSection, PolicyResourceSectionDslBuilder> {
//            scenario {
//                id = "minimum"
//                given(PolicyResourceSectionDslBuilder()) {
//                    flowSchema { }
//                    flowSchemaList {
//                        items {
//                            flowSchemaItem {  }
//                        }
//                    }
//                    limitRange { }
//                    limitRangeList {
//                        items {
//                            limitRangeItem { }
//                        }
//                    }
//                    networkPolicy { }
//                    networkPolicyList {
//                        items {
//                            networkPolicyItem { }
//                        }
//                    }
//                    podDisruptionBudget { }
//                    podDisruptionBudgetList {
//                        items {
//                            podDisruptionBudgetItem { }
//                        }
//                    }
//                    priorityLevelConfiguration { }
//                    priorityLevelConfigurationList {
//                        items {
//                            priorityLevelConfigurationItem { }
//                        }
//                    }
//                    resourceQuota { }
//                    resourceQuotaList {
//                        items {
//                            resourceQuotaItem { }
//                        }
//                    }
//                    validatingAdmissionPolicy { }
//                    validatingAdmissionPolicyList {
//                        items {
//                            validatingAdmissionPolicyItem { }
//                        }
//                    }
//                    validatingAdmissionPolicyBinding { }
//                    validatingAdmissionPolicyBindingList {
//                        items {
//                            validatingAdmissionPolicyBindingItem { }
//                        }
//                    }
//                }
//                expected = PolicyResourceSection(
//                    resources = listOf(
//                        FlowSchema(),
//                        FlowSchemaList(items = listOf(FlowSchema())),
//                        LimitRange(),
//                        LimitRangeList(items = listOf(LimitRange())),
//                        NetworkPolicy(),
//                        NetworkPolicyList(items = listOf(NetworkPolicy())),
//                        PodDisruptionBudget(),
//                        PodDisruptionBudgetList(items = listOf(PodDisruptionBudget())),
//                        PriorityLevelConfiguration(),
//                        PriorityLevelConfigurationList(items = listOf(PriorityLevelConfiguration())),
//                        ResourceQuota(),
//                        ResourceQuotaList(items = listOf(ResourceQuota())),
//                        ValidatingAdmissionPolicy(),
//                        ValidatingAdmissionPolicyBinding(),
//                        ValidatingAdmissionPolicyBindingList(items = listOf(ValidatingAdmissionPolicyBinding())),
//                        ValidatingAdmissionPolicyList(items = listOf(ValidatingAdmissionPolicy()))
//                    )
//                )
//            }
//        }
//
//        private val FAILURE_POSSIBILITIES = possibilities<PolicyResourceSection, PolicyResourceSectionDslBuilder> {
//            requireNotEmptyScenario("resources") {
//                given(PolicyResourceSectionDslBuilder())
//            }
//        }
//    }
//}