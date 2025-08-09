package io.violabs.picard

import java.time.LocalDateTime
import kotlin.reflect.KClass

// Import all test classes for KClass references
import io.violabs.picard.v2.resources.authentication.certificate.CertificateSigningRequestSpecTest
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverSpecTest
import io.violabs.picard.v2.resources.storage.csi.node.CsiNodeDriverTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceColumnDefinitionTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceConversionTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionNamesTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionSpecTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceDefinitionVersionTest
import io.violabs.picard.v2.resources.extend.resource.custom.CustomResourceSubresourceScaleTest
import io.violabs.picard.v2.resources.workload.deployment.DeploymentStatusTest
import io.violabs.picard.v2.resources.extend.deviceclass.DeviceClassListTest
import io.violabs.picard.v2.resources.workload.resource.claim.device.request.DeviceRequestTest
import io.violabs.picard.v2.resources.authorization.review.access.subject.attributes.FieldSelectorRequirementTest
import io.violabs.picard.v2.resources.policy.schema.flow.FlowSchemaStatusTest
import io.violabs.picard.v2.resources.workload.autoscaling.pod.HorizontalPodAutoscalerStatusTest
import io.violabs.picard.v2.resources.cluster.lease.candidate.LeaseCandidateSpecTest
import io.violabs.picard.v2.resources.extend.webhook.mutating.MutatingWebhookTest
import io.violabs.picard.v2.resources.policy.network.NetworkPolicyPortTest
import io.violabs.picard.v2.resources.cluster.node.NodeSystemInfoTest
import io.violabs.picard.v2.resources.policy.schema.rule.NonResourcePolicyRuleTest
import io.violabs.picard.v2.resources.authorization.review.rules.NonResourceRuleTest
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.ObjectMetricStatusTest
import io.violabs.picard.v2.resources.extend.deviceclass.config.OpaqueDeviceConfigurationTest
import io.violabs.picard.v2.resources.policy.disruption.PodDisruptionBudgetSpecTest
import io.violabs.picard.v2.resources.workload.batch.job.policy.failure.PodFailurePolicyOnExitCodesRequirementTest
import io.violabs.picard.v2.resources.workload.pod.PodListTest
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.PodsMetricStatusTest
import io.violabs.picard.v2.resources.authorization.role.PolicyRuleTest
import io.violabs.picard.v2.resources.policy.schema.rule.ResourcePolicyRuleTest
import io.violabs.picard.v2.resources.authorization.review.rules.ResourceRuleTest
import io.violabs.picard.v2.resources.extend.resource.custom.SelectableFieldTest
import io.violabs.picard.v2.resources.authorization.review.rules.SelfSubjectRulesReviewSpecTest
import io.violabs.picard.v2.resources.storage.version.migration.StorageVersionMigrationStatusTest
import io.violabs.picard.v2.resources.authentication.token.request.TokenRequestSpecTest
import io.violabs.picard.v2.resources.storage.selector.label.TopologySelectorLabelRequirementTest
import io.violabs.picard.v2.resources.extend.webhook.validating.ValidatingWebhookTest
import io.violabs.picard.v2.resources.extend.resource.json.schema.ValidationRuleTest
import io.violabs.picard.v2.resources.extend.resource.webhook.WebhookConversionTest

/**
 * Summary of test failures after requireNotEmptyScenario migration
 * Generated: 2025-08-08 23:09:33
 * Total failed classes: 38
 * Total failed tests: 64
 */
object TestFailureSummary {
    data class TestFailure(
        val method: String,
        val reason: String
    )
    
    val failures: Map<KClass<*>, List<TestFailure>> = mapOf(
        CertificateSigningRequestSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing request",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing signerName",
                reason = "no exception thrown"
            )
        ),
        CsiDriverSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing attachRequired",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing podInfoOnMount",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing requiresRepublish",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing seLinuxMount",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing storageCapacity",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing fsGroupPolicy",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing tokenRequests",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing volumeLifecycleModes",
                reason = "no exception thrown"
            )
        ),
        CsiNodeDriverTest::class to listOf(
            TestFailure(
                method = "build failure path - missing nodeID",
                reason = "no exception thrown"
            )
        ),
        CustomResourceColumnDefinitionTest::class to listOf(
            TestFailure(
                method = "build failure path - missing jsonPath",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing name",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing type",
                reason = "no exception thrown"
            )
        ),
        CustomResourceConversionTest::class to listOf(
            TestFailure(
                method = "build failure path - missing strategy",
                reason = "no exception thrown"
            )
        ),
        CustomResourceDefinitionNamesTest::class to listOf(
            TestFailure(
                method = "build failure path - missing kind",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing plural",
                reason = "no exception thrown"
            )
        ),
        CustomResourceDefinitionSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing group",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing names",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing scope",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing versions",
                reason = "no exception thrown"
            )
        ),
        CustomResourceDefinitionVersionTest::class to listOf(
            TestFailure(
                method = "build failure path - missing name",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing served",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing storage",
                reason = "no exception thrown"
            )
        ),
        CustomResourceSubresourceScaleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing specReplicasPath",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing statusReplicasPath",
                reason = "no exception thrown"
            )
        ),
        DeploymentStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing replicas",
                reason = "no exception thrown"
            )
        ),
        DeviceClassListTest::class to listOf(
            TestFailure(
                method = "build happy path - minimum",
                reason = "no exception thrown"
            )
        ),
        DeviceRequestTest::class to listOf(
            TestFailure(
                method = "build failure path - missing name",
                reason = "no exception thrown"
            )
        ),
        FieldSelectorRequirementTest::class to listOf(
            TestFailure(
                method = "build failure path - missing requirements",
                reason = "no exception thrown"
            )
        ),
        FlowSchemaStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing conditions",
                reason = "no exception thrown"
            )
        ),
        HorizontalPodAutoscalerStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing currentReplicas",
                reason = "no exception thrown"
            )
        ),
        LeaseCandidateSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing leaseName",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing preferredStrategies",
                reason = "no exception thrown"
            )
        ),
        MutatingWebhookTest::class to listOf(
            TestFailure(
                method = "build failure path - missing admissionReviewVersions",
                reason = "no exception thrown"
            )
        ),
        NetworkPolicyPortTest::class to listOf(
            TestFailure(
                method = "build happy path - minimum",
                reason = "no exception thrown"
            )
        ),
        NodeSystemInfoTest::class to listOf(
            TestFailure(
                method = "build failure path - missing bootID",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing machineID",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing systemUUID",
                reason = "no exception thrown"
            )
        ),
        NonResourcePolicyRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing nonResourceUrls",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        NonResourceRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        ObjectMetricStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing pods",
                reason = "no exception thrown"
            )
        ),
        OpaqueDeviceConfigurationTest::class to listOf(
            TestFailure(
                method = "build failure path - missing driver",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing parameters",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing driver",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing parameters",
                reason = "no exception thrown"
            )
        ),
        PodDisruptionBudgetSpecTest::class to listOf(
            TestFailure(
                method = "build happy path - int or string",
                reason = "no exception thrown"
            )
        ),
        PodFailurePolicyOnExitCodesRequirementTest::class to listOf(
            TestFailure(
                method = "build failure path - missing values",
                reason = "no exception thrown"
            )
        ),
        PodListTest::class to listOf(
            TestFailure(
                method = "build failure path - missing items",
                reason = "no exception thrown"
            )
        ),
        PodsMetricStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing resource",
                reason = "no exception thrown"
            )
        ),
        PolicyRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        ResourcePolicyRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing apiGroups",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing resources",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        ResourceRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        SelectableFieldTest::class to listOf(
            TestFailure(
                method = "build failure path - missing jsonPath",
                reason = "no exception thrown"
            )
        ),
        SelfSubjectRulesReviewSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing namespace",
                reason = "no exception thrown"
            )
        ),
        StorageVersionMigrationStatusTest::class to listOf(
            TestFailure(
                method = "build failure path - missing conditions",
                reason = "no exception thrown"
            )
        ),
        TokenRequestSpecTest::class to listOf(
            TestFailure(
                method = "build failure path - missing audiences",
                reason = "no exception thrown"
            )
        ),
        TopologySelectorLabelRequirementTest::class to listOf(
            TestFailure(
                method = "build failure path - missing values",
                reason = "no exception thrown"
            )
        ),
        ValidatingWebhookTest::class to listOf(
            TestFailure(
                method = "build failure path - missing admissionReviewVersions",
                reason = "no exception thrown"
            )
        ),
        ValidationRuleTest::class to listOf(
            TestFailure(
                method = "build failure path - missing rule",
                reason = "no exception thrown"
            )
        ),
        WebhookConversionTest::class to listOf(
            TestFailure(
                method = "build failure path - missing conversionReviewVersions",
                reason = "no exception thrown"
            )
        )
    )
    
    fun printSummary() {
        println("Test Failure Summary")
        println("=".repeat(50))
        println("Total failed classes: ${failures.size}")
        println("Total failed tests: ${failures.values.sumOf { it.size }}")
        println()
        
        failures.forEach { (testClass, testFailures) ->
            println("${testClass.simpleName}:")
            testFailures.forEach { failure ->
                println("  - ${failure.method}: ${failure.reason}")
            }
            println()
        }
    }
}