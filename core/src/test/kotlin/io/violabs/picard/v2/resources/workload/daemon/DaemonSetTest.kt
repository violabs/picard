//package io.violabs.picard.v2.resources.workload.daemon
//
//import io.violabs.picard.Common
//import io.violabs.picard.Common.sharedSelector
//import io.violabs.picard.SuccessBuildSim
//import io.violabs.picard.domain.BooleanType
//import io.violabs.picard.domain.k8sResources.IntOrString
//import io.violabs.picard.domain.k8sResources.workload.podTemplate.PodTemplate
//import io.violabs.picard.possibilities
//import io.violabs.picard.v2.common.ObjectMeta
//import org.junit.jupiter.api.BeforeAll
//
//class DaemonSetTest : SuccessBuildSim<DaemonSet, DaemonSetDslBuilder>() {
//    companion object {
//        @JvmStatic
//        @BeforeAll
//        fun setup() = buildSetup(
//            DaemonSetTest::class,
//            SUCCESS_POSSIBILITIES
//        )
//
//        private val SUCCESS_POSSIBILITIES = possibilities<DaemonSet, DaemonSetDslBuilder> {
//            scenario {
//                id = "minimum"
//                given(DaemonSetDslBuilder()) {
//                    spec {
//                        selector {
//                            sharedSelector()
//                        }
//                        template = PodTemplateSpec()
//                    }
//                }
//                expected = DaemonSet(
//                    spec = DaemonSetSpec(
//                        selector = Common.LABEL_SELECTOR,
//                        template = PodTemplateSpec()
//                    )
//                )
//            }
//
//            scenario {
//                id = "full"
//                given(DaemonSetDslBuilder()) {
//                    metadata {
//                        name = PLACEHOLDER
//                        namespace = PLACEHOLDER
//                    }
//                    spec {
//                        selector {
//                            sharedSelector()
//                        }
//                        template = PodTemplateSpec()
//                        minReadySeconds = 1
//                        updateStrategy {
//                            type = DaemonSetUpdateStrategy.Type.RollingUpdate
//                            rollingUpdate {
//                                maxUnavailable = IntOrString(1)
//                                maxSurge = IntOrString(1)
//                            }
//                        }
//                        revisionHistoryLimit = 1
//                    }
//                    status {
//                        numberReady = 1
//                        numberMisscheduled = 1
//                        desiredNumberScheduled = 1
//                        currentNumberScheduled = 1
//                        numberAvailable = 1
//                        numberUnavailable = 1
//                        updatedNumberScheduled = 1
//                        collisionCount = 1
//                        conditions {
//                            daemonSetCondition {
//                                status = BooleanType.True
//                                type = PLACEHOLDER
//                                lastTransitionTime = NOW
//                                message = PLACEHOLDER
//                                reason = PLACEHOLDER
//                            }
//                        }
//                        observedGeneration = 1
//                    }
//                }
//                expected = DaemonSet(
//                    metadata = ObjectMeta(
//                        name = PLACEHOLDER,
//                        namespace = PLACEHOLDER
//                    ),
//                    spec = DaemonSetSpec(
//                        selector = Common.LABEL_SELECTOR,
//                        template = PodTemplateSpec(),
//                        minReadySeconds = 1,
//                        updateStrategy = DaemonSetUpdateStrategy(
//                            type = DaemonSetUpdateStrategy.Type.RollingUpdate,
//                            rollingUpdate = RollingUpdateDaemonSet(
//                                maxUnavailable = IntOrString(1),
//                                maxSurge = IntOrString(1)
//                            )
//                        ),
//                        revisionHistoryLimit = 1
//                    ),
//                    status = DaemonSetStatus(
//                        numberReady = 1,
//                        numberMisscheduled = 1,
//                        desiredNumberScheduled = 1,
//                        currentNumberScheduled = 1,
//                        numberAvailable = 1,
//                        numberUnavailable = 1,
//                        updatedNumberScheduled = 1,
//                        collisionCount = 1,
//                        conditions = listOf(
//                            DaemonSetCondition(
//                                status = BooleanType.True,
//                                type = PLACEHOLDER,
//                                lastTransitionTime = NOW,
//                                message = PLACEHOLDER,
//                                reason = PLACEHOLDER
//                            )
//                        ),
//                        observedGeneration = 1
//                    )
//                )
//            }
//        }
//    }
//}