package io.violabs.picard.v2.resources.workload.pod

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.pod.container.Container
import io.violabs.picard.v2.resources.workload.pod.container.ContainerStatusDslBuilder
import org.junit.jupiter.api.BeforeAll

class PodTest : SuccessBuildSim<PodV2, PodV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodTest::class,
            SUCCESS_POSSIBILITIES
        )

        private fun ContainerStatusDslBuilder.Group.sharedContainerStatus() {
            containerStatus {
                name = PLACEHOLDER
                state {
                    terminated {
                        exitCode = 0
                        reason = PLACEHOLDER
                        message = PLACEHOLDER
                        startedAt = NOW
                        finishedAt = NOW
                    }
                }
                lastState {
                    terminated {
                        exitCode = 0
                        reason = PLACEHOLDER
                        message = PLACEHOLDER
                        startedAt = NOW
                        finishedAt = NOW
                    }
                }
                ready()
                restartCount = 0
                image = PLACEHOLDER
//                imageId = PLACEHOLDER
//                containerId = PLACEHOLDER
            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<PodV2, PodV2DslBuilder> {
            scenario {
                id = "minimum"
                given(PodV2DslBuilder())
                expected = PodV2()
            }

            scenario {
                id = "full"
                given(PodV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        containers {
                            container { 
                                name = PLACEHOLDER
                            }
                        }
                    }

                    status {
                        nominatedNodeName = PLACEHOLDER
                        hostIp = PLACEHOLDER
                        hostIps {
                            hostIp {
                                ip = PLACEHOLDER
                            }
                        }
                        startTime = NOW
                        phase = PodStatus.Phase.Succeeded
                        message = PLACEHOLDER
                        reason = PLACEHOLDER
                        podIp = PLACEHOLDER
                        podIps {
                            podIp {
                                ip = PLACEHOLDER
                            }
                        }

                        conditions {
                            condition {
                                type = PLACEHOLDER
                                status = BooleanType.True
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }

                        qosClass = PLACEHOLDER

                        initContainerStatuses {

                        }

                        containerStatuses {

                        }

                        ephemeralContainerStatuses {

                        }

                        resourceClaimStatuses {

                        }

                        resize = PLACEHOLDER
                    }
                }
                expected = PodV2(
                    metadata = OBJECT_META,
                    spec = PodSpec(
                        containers = listOf(Container(name = PLACEHOLDER))
                    ),
                    status = PodStatus()
                )
            }
        }
    }
}