package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference
import io.violabs.picard.domain.k8sResources.workload.metric.*
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerTest : SuccessBuildSim<HorizontalPodAutoscaler, HorizontalPodAutoscaler.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val CROSS_VERSION_OBJECT_REF = CrossVersionObjectReference(
            apiVersion = PLACEHOLDER,
            kind = PLACEHOLDER,
            name = PLACEHOLDER
        )

        private fun CrossVersionObjectReference.Builder.sharedCrossVersionObjectRef() {
            apiVersion = PLACEHOLDER
            kind = PLACEHOLDER
            name = PLACEHOLDER
        }

        private val HPA_SCALING_RULES = HPAScalingRules(
            policies = listOf(
                HPAScalingPolicy(
                    type = PLACEHOLDER,
                    value = 1,
                    periodSeconds = 1
                )
            ),
            selectPolicy = PLACEHOLDER,
            stabilizationWindowSeconds = 1
        )

        private fun HPAScalingRules.Builder.sharedHPAScalingRules() {
            policies {
                policy {
                    type = PLACEHOLDER
                    value = 1
                    periodSeconds = 1
                }
            }
            selectPolicy = PLACEHOLDER
            stabilizationWindowSeconds = 1
        }

        private val HPA_BEHAVIOR = HorizontalPodAutoscalerBehavior(
            scaleDown = HPA_SCALING_RULES,
            scaleUp = HPA_SCALING_RULES
        )

        private val METRIC_TARGET = MetricTarget(
            type = MetricTarget.Type.AverageValue,
            averageUtilization = 1,
            averageValue = QUANTITY,
            value = QUANTITY
        )

        private fun MetricTarget.Builder.sharedMetricTarget() {
            type = MetricTarget.Type.AverageValue
            averageUtilization = 1
            averageValue(PLACEHOLDER)
            value(PLACEHOLDER)
        }

        private val METRIC_IDENTIFIER = MetricIdentifier(
            name = PLACEHOLDER,
            selector = LABEL_SELECTOR
        )

        private fun MetricIdentifier.Builder.sharedMetricIdentifier() {
            name = PLACEHOLDER
            selector {
                sharedSelector()
            }
        }

        private val EXTERNAL_METRIC_SOURCE = ExternalMetricSource(
            metric = METRIC_IDENTIFIER,
            target = METRIC_TARGET
        )

        private val OBJECT_METRIC_SOURCE = ObjectMetricSource(
            describedObject = CROSS_VERSION_OBJECT_REF,
            metric = METRIC_IDENTIFIER,
            target = METRIC_TARGET
        )

        private val PODS_METRIC_SOURCE = PodsMetricSource(
            metric = METRIC_IDENTIFIER,
            target = METRIC_TARGET
        )

        private val RESOURCE_METRIC_SOURCE = ResourceMetricSource(
            name = PLACEHOLDER,
            target = METRIC_TARGET
        )

        private val CONTAINER_RESOURCE_METRIC_SOURCE = ContainerResourceMetricSource(
            container = PLACEHOLDER,
            name = PLACEHOLDER,
            target = METRIC_TARGET
        )

        private val METRIC_SPEC = MetricSpec(
            type = MetricType.Pods,
            containerResource = CONTAINER_RESOURCE_METRIC_SOURCE,
            external = EXTERNAL_METRIC_SOURCE,
            objectMetricSource = OBJECT_METRIC_SOURCE,
            pods = PODS_METRIC_SOURCE,
            resource = RESOURCE_METRIC_SOURCE
        )

        private val METRIC_VALUE_STATUS = MetricValueStatus(
            averageUtilization = 1,
            averageValue = QUANTITY,
            value = QUANTITY
        )

        private fun MetricValueStatus.Builder.sharedMetricValueStatus() {
            averageUtilization = 1
            averageValue(PLACEHOLDER)
            value(PLACEHOLDER)
        }

        private val CONTAINER_RESOURCE_METRIC_STATUS = ContainerResourceMetricStatus(
            container = PLACEHOLDER,
            current = METRIC_VALUE_STATUS,
            name = PLACEHOLDER,
        )

        private fun ContainerResourceMetricStatus.Builder.sharedContainerResourceMetricStatus() {
            container = PLACEHOLDER
            current {
                sharedMetricValueStatus()
            }
            name = PLACEHOLDER
        }

        private val EXTERNAL_METRIC_STATUS = ExternalMetricStatus(
            current = METRIC_VALUE_STATUS,
            metric = METRIC_IDENTIFIER
        )

        private val OBJECT_METRIC_STATUS = ObjectMetricStatus(
            current = METRIC_VALUE_STATUS,
            describedObject = CROSS_VERSION_OBJECT_REF,
            metric = METRIC_IDENTIFIER,
            pods = PODS_METRIC_SOURCE
        )

        private val PODS_METRIC_STATUS = PodsMetricStatus(
            current = METRIC_VALUE_STATUS,
            metric = METRIC_IDENTIFIER,
            resource = CONTAINER_RESOURCE_METRIC_STATUS
        )

        private val RESOURCE_METRIC_STATUS = ResourceMetricStatus(
            current = METRIC_VALUE_STATUS,
            name = PLACEHOLDER
        )

        private val METRIC_STATUS = MetricStatus(
            type = MetricType.Pods,
            containerResource = CONTAINER_RESOURCE_METRIC_STATUS,
            external = EXTERNAL_METRIC_STATUS,
            objectMetricStatus = OBJECT_METRIC_STATUS,
            pods = PODS_METRIC_STATUS,
            resource = RESOURCE_METRIC_STATUS
        )

        private val SUCCESS_POSSIBILITIES = possibilities<HorizontalPodAutoscaler, HorizontalPodAutoscaler.Builder> {
            scenario {
                id = "minimum"
                given(HorizontalPodAutoscaler.Builder())
                expected = HorizontalPodAutoscaler()
            }

            scenario {
                id = "full"
                given(HorizontalPodAutoscaler.Builder()) {
                    sharedMetadata()
                    spec {
                        maxReplicas = 1
                        minReplicas = 1
                        targetCPUUtilizationPercentage = 1
                        scaleTargetRef { // cross version object reference
                            sharedCrossVersionObjectRef()
                        }
                        behavior {
                            scaleDown {
                                sharedHPAScalingRules()
                            }
                            scaleUp {
                                sharedHPAScalingRules()
                            }
                        }
                        metrics {
                            metricSpec {
                                type = MetricType.Pods
                                containerResource {
                                    container = PLACEHOLDER
                                    name = PLACEHOLDER
                                    target {
                                        sharedMetricTarget()
                                    }
                                }
                                external {
                                    metric {
                                        name = PLACEHOLDER
                                        sharedMetricIdentifier()
                                    }
                                    target {
                                        sharedMetricTarget()
                                    }
                                }
                                objectMetricSource {
                                    describedObject {
                                        sharedCrossVersionObjectRef()
                                    }
                                    metric {
                                        name = PLACEHOLDER
                                        sharedMetricIdentifier()
                                    }
                                    target {
                                        sharedMetricTarget()
                                    }
                                }
                                pods {
                                    metric {
                                        sharedMetricIdentifier()
                                    }

                                    target {
                                        sharedMetricTarget()
                                    }
                                }
                                resource {
                                    name = PLACEHOLDER
                                    target {
                                        sharedMetricTarget()
                                    }
                                }
                            }
                        }
                    }
                    this.status {
                        currentReplicas = 1
                        desiredReplicas = 1
                        lastScaleTime = NOW
                        observedGeneration = 1
                        currentCPUUtilizationPercentage = 1
                        conditions {
                            sharedCondition()
                        }
                        currentMetrics {
                            status {
                                type = MetricType.Pods
                                containerResource {
                                    sharedContainerResourceMetricStatus()
                                }
                                external {
                                    current {
                                        sharedMetricValueStatus()
                                    }
                                    metric {
                                        sharedMetricIdentifier()
                                    }
                                }
                                objectMetricStatus {
                                    current {
                                        sharedMetricValueStatus()
                                    }
                                    describedObject {
                                        sharedCrossVersionObjectRef()
                                    }
                                    metric {
                                        sharedMetricIdentifier()
                                    }
                                    pods {
                                        metric {
                                            sharedMetricIdentifier()
                                        }

                                        target {
                                            sharedMetricTarget()
                                        }
                                    }
                                }
                                pods {
                                    current {
                                        sharedMetricValueStatus()
                                    }
                                    metric {
                                        sharedMetricIdentifier()
                                    }
                                    resource {
                                        sharedContainerResourceMetricStatus()
                                    }
                                }
                                resource {
                                    name = PLACEHOLDER
                                    current {
                                        sharedMetricValueStatus()
                                    }
                                }
                            }
                        }
                    }
                }
                expected = HorizontalPodAutoscaler(
                    metadata = METADATA,
                    spec = HorizontalPodAutoscaler.Spec(
                        maxReplicas = 1,
                        minReplicas = 1,
                        targetCPUUtilizationPercentage = 1,
                        scaleTargetRef = CROSS_VERSION_OBJECT_REF,
                        behavior = HPA_BEHAVIOR,
                        metrics = listOf(METRIC_SPEC)
                    ),
                    status = HorizontalPodAutoscaler.Status(
                        currentReplicas = 1,
                        desiredReplicas = 1,
                        lastScaleTime = NOW,
                        observedGeneration = 1,
                        currentCPUUtilizationPercentage = 1,
                        conditions = listOf(CONDITION),
                        currentMetrics = listOf(METRIC_STATUS)
                    )
                )
            }
        }
    }
}