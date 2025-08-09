package io.violabs.picard

import java.time.LocalDateTime

/**
 * Summary of test failures after requireNotEmptyScenario migration
 * Generated: 2025-08-08 23:03:15
 * Total failed classes: 38
 * Total failed tests: 64
 */
object TestFailureSummary {
    data class TestFailure(
        val method: String,
        val reason: String
    )
    
    val failures = mapOf(
        "CertificateSigningRequestSpecTest" to listOf(
            TestFailure(
                method = "build failure path - missing request",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing signerName",
                reason = "no exception thrown"
            )
        ),
        "CsiDriverSpecTest" to listOf(
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
        "CsiNodeDriverTest" to listOf(
            TestFailure(
                method = "build failure path - missing nodeID",
                reason = "no exception thrown"
            )
        ),
        "CustomResourceColumnDefinitionTest" to listOf(
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
        "CustomResourceConversionTest" to listOf(
            TestFailure(
                method = "build failure path - missing strategy",
                reason = "no exception thrown"
            )
        ),
        "CustomResourceDefinitionNamesTest" to listOf(
            TestFailure(
                method = "build failure path - missing kind",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing plural",
                reason = "no exception thrown"
            )
        ),
        "CustomResourceDefinitionSpecTest" to listOf(
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
        "CustomResourceDefinitionVersionTest" to listOf(
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
        "CustomResourceSubresourceScaleTest" to listOf(
            TestFailure(
                method = "build failure path - missing specReplicasPath",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing statusReplicasPath",
                reason = "no exception thrown"
            )
        ),
        "DeploymentStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing replicas",
                reason = "no exception thrown"
            )
        ),
        "DeviceClassListTest" to listOf(
            TestFailure(
                method = "build happy path - minimum",
                reason = "no exception thrown"
            )
        ),
        "DeviceRequestTest" to listOf(
            TestFailure(
                method = "build failure path - missing name",
                reason = "no exception thrown"
            )
        ),
        "FieldSelectorRequirementTest" to listOf(
            TestFailure(
                method = "build failure path - missing requirements",
                reason = "no exception thrown"
            )
        ),
        "FlowSchemaStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing conditions",
                reason = "no exception thrown"
            )
        ),
        "HorizontalPodAutoscalerStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing currentReplicas",
                reason = "no exception thrown"
            )
        ),
        "LeaseCandidateSpecTest" to listOf(
            TestFailure(
                method = "build failure path - missing leaseName",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing preferredStrategies",
                reason = "no exception thrown"
            )
        ),
        "MutatingWebhookTest" to listOf(
            TestFailure(
                method = "build failure path - missing admissionReviewVersions",
                reason = "no exception thrown"
            )
        ),
        "NetworkPolicyPortTest" to listOf(
            TestFailure(
                method = "build happy path - minimum",
                reason = "no exception thrown"
            )
        ),
        "NodeSystemInfoTest" to listOf(
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
        "NonResourcePolicyRuleTest" to listOf(
            TestFailure(
                method = "build failure path - missing nonResourceUrls",
                reason = "no exception thrown"
            ),
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        "NonResourceRuleTest" to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        "ObjectMetricStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing pods",
                reason = "no exception thrown"
            )
        ),
        "OpaqueDeviceConfigurationTest" to listOf(
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
        "PodDisruptionBudgetSpecTest" to listOf(
            TestFailure(
                method = "build happy path - int or string",
                reason = "no exception thrown"
            )
        ),
        "PodFailurePolicyOnExitCodesRequirementTest" to listOf(
            TestFailure(
                method = "build failure path - missing values",
                reason = "no exception thrown"
            )
        ),
        "PodListTest" to listOf(
            TestFailure(
                method = "build failure path - missing items",
                reason = "no exception thrown"
            )
        ),
        "PodsMetricStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing resource",
                reason = "no exception thrown"
            )
        ),
        "PolicyRuleTest" to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        "ResourcePolicyRuleTest" to listOf(
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
        "ResourceRuleTest" to listOf(
            TestFailure(
                method = "build failure path - missing verbs",
                reason = "no exception thrown"
            )
        ),
        "SelectableFieldTest" to listOf(
            TestFailure(
                method = "build failure path - missing jsonPath",
                reason = "no exception thrown"
            )
        ),
        "SelfSubjectRulesReviewSpecTest" to listOf(
            TestFailure(
                method = "build failure path - missing namespace",
                reason = "no exception thrown"
            )
        ),
        "StorageVersionMigrationStatusTest" to listOf(
            TestFailure(
                method = "build failure path - missing conditions",
                reason = "no exception thrown"
            )
        ),
        "TokenRequestSpecTest" to listOf(
            TestFailure(
                method = "build failure path - missing audiences",
                reason = "no exception thrown"
            )
        ),
        "TopologySelectorLabelRequirementTest" to listOf(
            TestFailure(
                method = "build failure path - missing values",
                reason = "no exception thrown"
            )
        ),
        "ValidatingWebhookTest" to listOf(
            TestFailure(
                method = "build failure path - missing admissionReviewVersions",
                reason = "no exception thrown"
            )
        ),
        "ValidationRuleTest" to listOf(
            TestFailure(
                method = "build failure path - missing rule",
                reason = "no exception thrown"
            )
        ),
        "WebhookConversionTest" to listOf(
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
        
        failures.forEach { (className, testFailures) ->
            println("$className:")
            testFailures.forEach { failure ->
                println("  - ${failure.method}: ${failure.reason}")
            }
            println()
        }
    }
}