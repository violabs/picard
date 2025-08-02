package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.batch.job.policy.failure.PodFailurePolicy
import io.violabs.picard.v2.resources.workload.batch.job.policy.failure.PodFailurePolicyOnExitCodesRequirement
import io.violabs.picard.v2.resources.workload.batch.job.policy.failure.PodFailurePolicyOnPodConditionsPattern
import io.violabs.picard.v2.resources.workload.batch.job.policy.failure.PodFailurePolicyRule
import io.violabs.picard.v2.resources.workload.batch.job.policy.success.SuccessPolicy
import io.violabs.picard.v2.resources.workload.batch.job.policy.success.SuccessPolicyRule
import org.junit.jupiter.api.BeforeAll

class JobTest : SuccessBuildSim<JobV2, JobV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            JobTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val POD_FAILURE_POLICY_RULE = PodFailurePolicyRule(
            action = PodFailurePolicyRule.Action.FailJob,
            onExitCodes = PodFailurePolicyOnExitCodesRequirement(
                operator = PodFailurePolicyOnExitCodesRequirement.Operator.In,
                values = listOf(1),
                containerName = PLACEHOLDER
            ),
            onPodConditions = listOf(
                PodFailurePolicyOnPodConditionsPattern(
                    status = PLACEHOLDER,
                    type = PLACEHOLDER
                )
            )
        )

        private val SUCCESS_POLICY_RULE = SuccessPolicyRule(
            succeededCount = 1,
            succeededIndexes = PLACEHOLDER
        )

        private val JOB_CONDITION = JobCondition(
            status = BooleanType.True,
            type = JobCondition.Type.Complete,
            lastProbeTime = NOW,
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER
        )

        private val SUCCESS_POSSIBILITIES = possibilities<JobV2, JobV2DslBuilder> {
            scenario {
                id = "minimum"
                given(JobV2DslBuilder()) {
                    spec {
                        template = PodTemplate.Spec()
                    }
                }
                expected = JobV2(
                    spec = JobSpec(
                        template = PodTemplate.Spec()
                    )
                )
            }

            scenario {
                id = "full"
                given(JobV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        template = PodTemplate.Spec()
                        parallelism = 1
                        completions = 1
                        completionMode = JobSpec.CompletionMode.NonIndexed
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
                                podFailurePolicyRule {
                                    action = PodFailurePolicyRule.Action.FailJob
                                    onExitCodes {
                                        operator = PodFailurePolicyOnExitCodesRequirement.Operator.In
                                        values(1)
                                        containerName = PLACEHOLDER
                                    }
                                    onPodConditions {
                                        podFailurePolicyOnPodConditionsPattern {
                                            status = PLACEHOLDER
                                            type = PLACEHOLDER
                                        }
                                    }
                                }
                            }
                        }
                        successPolicy {
                            rules {
                                successPolicyRule {
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
                    status {
                        startTime = NOW
                        completionTime = NOW
                        active = 1
                        failed = 1
                        succeeded = 1
                        completedIndexes = PLACEHOLDER
                        conditions {
                            jobCondition {
                                status = BooleanType.True
                                type = JobCondition.Type.Complete
                                lastProbeTime = NOW
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
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
                expected = JobV2(
                    metadata = Common.OBJECT_META,
                    spec = JobSpec(
                        template = PodTemplate.Spec(),
                        parallelism = 1,
                        completions = 1,
                        completionMode = JobSpec.CompletionMode.NonIndexed,
                        backoffLimit = 1,
                        activeDeadlineSeconds = 1,
                        ttlSecondsAfterFinished = 1,
                        suspend = true,
                        selector = Common.LABEL_SELECTOR,
                        manualSelector = true,
                        podFailurePolicy = PodFailurePolicy(
                            rules = listOf(POD_FAILURE_POLICY_RULE)
                        ),
                        successPolicy = SuccessPolicy(
                            rules = listOf(SUCCESS_POLICY_RULE)
                        ),
                        backoffLimitPerIndex = 1,
                        managedBy = PLACEHOLDER,
                        maxFailedIndexes = 1,
                        podReplacementPolicy = PLACEHOLDER
                    ),
                    status = JobStatus(
                        startTime = NOW,
                        completionTime = NOW,
                        active = 1,
                        failed = 1,
                        succeeded = 1,
                        completedIndexes = PLACEHOLDER,
                        conditions = listOf(JOB_CONDITION),
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