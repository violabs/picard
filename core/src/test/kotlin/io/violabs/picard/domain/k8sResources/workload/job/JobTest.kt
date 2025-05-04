package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.Operator
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class JobTest : SuccessBuildSim<Job, Job.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            JobTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val POD_FAILURE_POLICY_RULE = PodFailurePolicyRule(
            action = PodFailurePolicyRule.Action.FailIndex,
            onExitCodes = OnExitCodesRequirement(
                operator = Operator.In,
                values = listOf(1),
                containerName = PLACEHOLDER
            ),
            onPodConditions = listOf(
                OnPodConditionsPattern(
                    status = BooleanType.True,
                    type = PLACEHOLDER,
                )
            )
        )

        private val POD_SUCCESS_POLICY_RULE = PodSuccessPolicyRule(
            succeededCount = 1,
            succeededIndexes = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Job, Job.Builder> {
            scenario {
                id = "minimum"
                given(Job.Builder())
                expected = Job()
            }

            scenario {
                id = "full without template spec"
                given(Job.Builder()) {
                    sharedMetadata()
                    spec {
                        template {}
                        parallelism = 1
                        completions = 1
                        completionMode = Job.Spec.CompletionMode.NonIndexed
                        backoffLimit = 1
                        activeDeadlineSeconds = 1
                        ttlSecondsAfterFinished = 1
                        suspend()
                        selector {
                            sharedSelector()
                        }
                        manualSelector()
                        podFailurePolicy {
                            rules {
                                rule {
                                    action = PodFailurePolicyRule.Action.FailIndex
                                    onExitCodes {
                                        operator = Operator.In
                                        values(1)
                                        containerName = PLACEHOLDER
                                    }
                                    onPodConditions {
                                        pattern {
                                            status = BooleanType.True
                                            type = PLACEHOLDER
                                        }
                                    }
                                }
                            }
                        }
                        successPolicy {
                            rules {
                                rule {
                                    succeededCount = 1
                                    succeededIndexes = PLACEHOLDER
                                }
                            }
                        }
                        backoffLimitPerIndex = 1
                        managedBy = PLACEHOLDER
                        maxFailedIndexes = 1
                        podReplacementPolicy = PLACEHOLDER
                    }
                    this.status {
                        startTime = NOW
                        completionTime = NOW
                        active = 1
                        failed = 1
                        succeeded = 1
                        completedIndexes = PLACEHOLDER
                        conditions {
                            sharedNodeCondition()
                        }
                        uncountedTerminatedPods {
                            failed(PLACEHOLDER)
                            succeeded(PLACEHOLDER)
                        }
                        ready = 1
                        failedIndexes = PLACEHOLDER
                        terminating = 1
                    }
                }
                expected = Job(
                    metadata = METADATA,
                    spec = Job.Spec(
                        template = PodTemplate.Spec(),
                        parallelism = 1,
                        completions = 1,
                        completionMode = Job.Spec.CompletionMode.NonIndexed,
                        backoffLimit = 1,
                        activeDeadlineSeconds = 1,
                        ttlSecondsAfterFinished = 1,
                        suspend = true,
                        selector = LABEL_SELECTOR,
                        manualSelector = true,
                        podFailurePolicy = PodFailurePolicy(
                            rules = listOf(POD_FAILURE_POLICY_RULE)
                        ),
                        successPolicy = PodSuccessPolicy(
                            rules = listOf(POD_SUCCESS_POLICY_RULE)
                        ),
                        backoffLimitPerIndex = 1,
                        managedBy = PLACEHOLDER,
                        maxFailedIndexes = 1,
                        podReplacementPolicy = PLACEHOLDER
                    ),
                    status = Job.Status(
                        startTime = NOW,
                        completionTime = NOW,
                        active = 1,
                        failed = 1,
                        succeeded = 1,
                        completedIndexes = PLACEHOLDER,
                        conditions = listOf(NODE_CONDITION),
                        uncountedTerminatedPods = UncountedTerminatedPods(
                            failed = PLACEHOLDER_LIST,
                            succeeded = PLACEHOLDER_LIST
                        ),
                        ready = 1,
                        failedIndexes = PLACEHOLDER,
                        terminating = 1
                    )
                )
            }
        }
    }
}