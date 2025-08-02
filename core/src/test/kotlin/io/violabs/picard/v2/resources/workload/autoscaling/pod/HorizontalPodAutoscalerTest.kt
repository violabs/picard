package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReference
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReferenceDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifier
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricIdentifierDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricSpec
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTarget
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricTargetDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.metric.MetricType
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ContainerResourceMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ExternalMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ObjectMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.PodsMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.source.ResourceMetricSource
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.ContainerResourceMetricStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.ExternalMetricStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.MetricStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.MetricValueStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.MetricValueStatusDslBuilder
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.ObjectMetricStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.PodsMetricStatus
import io.violabs.picard.v2.resources.workload.autoscaling.metric.status.ResourceMetricStatus
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerTest : SuccessBuildSim<HorizontalPodAutoscalerV2, HorizontalPodAutoscalerV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerTest::class,
            SUCCESS_POSSIBILITIES
        )

        private fun CrossVersionObjectReferenceDslBuilder.sharedReference() {
            kind = PLACEHOLDER
            name = PLACEHOLDER
            apiVersion = PLACEHOLDER
        }

        private val REFERENCE = CrossVersionObjectReference(
            kind = PLACEHOLDER,
            name = PLACEHOLDER,
            apiVersion = PLACEHOLDER
        )

        private fun MetricTargetDslBuilder.sharedTarget() {
            type = MetricTarget.Type.Utilization
            averageUtilization = 1
            averageValue(PLACEHOLDER)
            value(PLACEHOLDER)
        }

        private val METRIC_TARGET = MetricTarget(
            type = MetricTarget.Type.Utilization,
            averageUtilization = 1,
            averageValue = QUANTITY,
            value = QUANTITY
        )

        private fun MetricIdentifierDslBuilder.sharedIdentifier() {
            name = PLACEHOLDER
            selector {
                sharedSelector()
            }
        }

        private val METRIC_IDENTIFIER = MetricIdentifier(
            name = PLACEHOLDER,
            selector = Common.LABEL_SELECTOR
        )

        private fun HpaScalingRulesDslBuilder.sharedRules() {
            stabilizationWindowSeconds = 1
            selectPolicy = PLACEHOLDER
            tolerance(PLACEHOLDER)
            policies {
                hpaScalingPolicy {
                    type = PLACEHOLDER
                    value = 1
                    periodSeconds = 1
                }
            }
        }

        private val HPA_SCALING_RULES = HpaScalingRules(
            stabilizationWindowSeconds = 1,
            selectPolicy = PLACEHOLDER,
            tolerance = QUANTITY,
            policies = listOf(
                HpaScalingPolicy(
                    type = PLACEHOLDER,
                    value = 1,
                    periodSeconds = 1
                )
            )
        )

        private fun MetricValueStatusDslBuilder.sharedStatus() {
            averageUtilization = 1
            averageValue(PLACEHOLDER)
            value(PLACEHOLDER)
        }

        private val METRIC_VALUE_STATUS = MetricValueStatus(
            averageUtilization = 1,
            averageValue = QUANTITY,
            value = QUANTITY
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerV2, HorizontalPodAutoscalerV2DslBuilder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscalerV2DslBuilder()) {
                        spec {
                            maxReplicas = 1
                            scaleTargetRef {
                                kind = PLACEHOLDER
                                name = PLACEHOLDER
                            }
                        }
                    }
                    expected = HorizontalPodAutoscalerV2(
                        spec = HorizontalPodAutoscalerSpec(
                            maxReplicas = 1,
                            scaleTargetRef = CrossVersionObjectReference(
                                kind = PLACEHOLDER,
                                name = PLACEHOLDER
                            )
                        )
                    )
                }

                scenario {
                    id = "full"
                    given(HorizontalPodAutoscalerV2DslBuilder()) {
                        metadata {
                            sharedObjectMeta()
                        }
                        spec {
                            minReplicas = 1
                            maxReplicas = 1
                            scaleTargetRef {
                                sharedReference()
                            }
                            metrics {
                                metricSpec {
                                    type = MetricType.Pods

                                    containerResource {
                                        container = PLACEHOLDER
                                        name = PLACEHOLDER
                                        target { sharedTarget() }
                                    }

                                    external {
                                        metric { sharedIdentifier() }
                                        target { sharedTarget() }
                                    }

                                    objectSource {
                                        describedObject { sharedReference() }
                                        metric { sharedIdentifier() }
                                        target { sharedTarget() }
                                    }

                                    pods {
                                        metric { sharedIdentifier() }
                                        target { sharedTarget() }
                                    }

                                    resource {
                                        name = PLACEHOLDER
                                        target { sharedTarget() }
                                    }
                                }
                            }

                            behavior {
                                scaleUp { sharedRules() }
                                scaleDown { sharedRules() }
                            }
                        }
                        status {
                            desiredReplicas = 1
                            currentReplicas = 1
                            observedGeneration = 1
                            lastScaleTime = NOW
                            conditions {
                                horizontalPodAutoscalerCondition {
                                    type = PLACEHOLDER
                                    status = BooleanType.True
                                    reason = PLACEHOLDER
                                    message = PLACEHOLDER
                                }
                            }

                            currentMetrics {
                                metricStatus {
                                    type = MetricType.Pods

                                    containerResource {
                                        container = PLACEHOLDER
                                        name = PLACEHOLDER
                                        current { sharedStatus() }
                                    }

                                    external {
                                        current { sharedStatus() }
                                        metric { sharedIdentifier() }
                                    }

                                    objectSource {
                                        describedObject { sharedReference() }
                                        metric { sharedIdentifier() }
                                        current { sharedStatus() }
                                    }

                                    pods {
                                        current { sharedStatus() }
                                        metric { sharedIdentifier() }
                                    }

                                    resource {
                                        name = PLACEHOLDER
                                        current { sharedStatus() }
                                    }
                                }
                            }
                        }
                    }
                    expected = HorizontalPodAutoscalerV2(
                        metadata = Common.OBJECT_META,
                        spec = HorizontalPodAutoscalerSpec(
                            minReplicas = 1,
                            maxReplicas = 1,
                            scaleTargetRef = REFERENCE,
                            metrics = listOf(
                                MetricSpec(
                                    type = MetricType.Pods,
                                    containerResource = ContainerResourceMetricSource(
                                        container = PLACEHOLDER,
                                        name = PLACEHOLDER,
                                        target = METRIC_TARGET
                                    ),
                                    external = ExternalMetricSource(
                                        metric = METRIC_IDENTIFIER,
                                        target = METRIC_TARGET
                                    ),
                                    objectSource = ObjectMetricSource(
                                        describedObject = REFERENCE,
                                        metric = METRIC_IDENTIFIER,
                                        target = METRIC_TARGET
                                    ),
                                    pods = PodsMetricSource(
                                        metric = METRIC_IDENTIFIER,
                                        target = METRIC_TARGET
                                    ),
                                    resource = ResourceMetricSource(
                                        name = PLACEHOLDER,
                                        target = METRIC_TARGET
                                    )
                                )
                            ),
                            behavior = HorizontalPodAutoscalerBehavior(
                                scaleUp = HPA_SCALING_RULES,
                                scaleDown = HPA_SCALING_RULES
                            )
                        ),
                        status = HorizontalPodAutoscalerStatus(
                            desiredReplicas = 1,
                            currentReplicas = 1,
                            observedGeneration = 1,
                            lastScaleTime = NOW,
                            conditions = listOf(
                                HorizontalPodAutoscalerCondition(
                                    type = PLACEHOLDER,
                                    status = BooleanType.True,
                                    reason = PLACEHOLDER,
                                    message = PLACEHOLDER
                                )
                            ),
                            currentMetrics = listOf(
                                MetricStatus(
                                    type = MetricType.Pods,
                                    containerResource = ContainerResourceMetricStatus(
                                        container = PLACEHOLDER,
                                        name = PLACEHOLDER,
                                        current = METRIC_VALUE_STATUS
                                    ),
                                    external = ExternalMetricStatus(
                                        current = METRIC_VALUE_STATUS,
                                        metric = METRIC_IDENTIFIER
                                    ),
                                    objectSource = ObjectMetricStatus(
                                        describedObject = REFERENCE,
                                        metric = METRIC_IDENTIFIER,
                                        current = METRIC_VALUE_STATUS
                                    ),
                                    pods = PodsMetricStatus(
                                        current = METRIC_VALUE_STATUS,
                                        metric = METRIC_IDENTIFIER
                                    ),
                                    resource = ResourceMetricStatus(
                                        name = PLACEHOLDER,
                                        current = METRIC_VALUE_STATUS
                                    )
                                )
                            )
                        )
                    )
                }
            }
    }
}