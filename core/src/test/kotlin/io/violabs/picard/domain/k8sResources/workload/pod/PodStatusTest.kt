package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.common.quantity
import io.violabs.picard.domain.k8sResources.Quantity
import io.violabs.picard.domain.k8sResources.workload.pod.container.*
import io.violabs.picard.domain.k8sResources.workload.pod.resource.PodResourceClaimStatus
import io.violabs.picard.domain.k8sResources.workload.pod.resource.ResourceHealth
import io.violabs.picard.domain.k8sResources.workload.pod.resource.ResourceStatus
import io.violabs.picard.domain.k8sResources.workload.pod.volume.VolumeMountStatus
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import java.time.LocalDateTime

class PodStatusTest : SuccessBuildSim<PodStatus, PodStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodStatusTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NOW = LocalDateTime.now()

        private val CONTAINER_STATE = ContainerState(
            running = ContainerState.Running(NOW),
            terminated = ContainerState.Terminated(
                containerID = "test_container_id",
                exitCode = 1,
                finishedAt = NOW,
                message = "test_message_terminated",
                reason = "test_reason_terminated",
                signal = 2,
                startedAt = NOW
            ),
            waiting = ContainerState.Waiting("test_wait", "test_wait_reason")
        )

        private val REQUIREMENTS = ContainerResourceRequirements(
            claims = listOf(
                ContainerResourceClaim(
                    name = "claim-name",
                    request = "100m"
                )
            ),
            limits = mapOf(
                "cpu" to Quantity("100m"),
            ),
            requests = mapOf(
                "cpu" to Quantity("100m"),
            ),
            resizePolicy = listOf(
                ResizePolicy(
                    resourceName = "cpu",
                    restartPolicy = "Always"
                )
            )
        )

        private val CONTAINER_STATUS = ContainerStatus(
            imageID = "start",
            image = "test/image",
            name = "test_name",
            ready = true,
            allocatedResources = mapOf("resource" to "one".quantity()),
            allocatedResourceStatus = listOf(
                ResourceStatus(
                    name = "resource_status_name",
                    image = "test/image",
                    imageID = "test_image",
                    resources = listOf(
                        ResourceHealth(
                            resourceID = "1",
                            health = ResourceHealth.Type.Healthy
                        )
                    ),
                    containerID = "test_container_id"
                )
            ),
            containerID = "test_container_id",
            lastState = CONTAINER_STATE,
            resources = REQUIREMENTS,
            restartCount = 1,
            started = true,
            state = CONTAINER_STATE,
            user = ContainerUser(
                linux = LinuxContainerUser(
                    gid = 1,
                    uid = 2,
                    supplementalGroups = listOf(3, 4)
                )
            ),
            volumeMounts = VolumeMountStatus(
                mountPath = "/test",
                name = "test_volume",
                readOnly = true,
                recursiveReadOnly = "read_only"
            )
        )

        private val CLAIM_STATUS = PodResourceClaimStatus(
            name = "test_resource_claim",
            resourceClaimName = "test_resource_claim_name",
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodStatus, PodStatusDslBuilder> {
            scenario {
                id = "minimum"
                given(PodStatusDslBuilder())
                expected = PodStatus()
            }

            scenario {
                id = "full"
                given(PodStatusDslBuilder()) {
                    nominatedNodeName = "test_nominated_node_name"
                    hostIP = "127.0.0.1"
                    hostIPs { addIp("0.0.0.0") }
                    startTime = NOW
                    phase = "test_phase"
                    message = "test_message"
                    reason = "test_reason"
                    podIP = "127.0.0.1"
                    podIPs { addIp("0.0.0.0") }
                    conditions {
                        sharedCondition()
                    }
                    qosClass = "test_qos_class"
                    initContainerStatuses { sharedContainerStatus() }
                    containerStatuses { sharedContainerStatus() }
                    ephemeralContainerStatuses { sharedContainerStatus() }
                    resourceClaimStatuses {
                        addPodResourceClaimStatus {
                            name = "test_resource_claim"
                            resourceClaimName = "test_resource_claim_name"
                        }
                    }
                    resize = "large"
                }
                expected = PodStatus(
                    nominatedNodeName = "test_nominated_node_name",
                    hostIP = "127.0.0.1",
                    hostIPs = listOf(
                        HostIP("0.0.0.0")
                    ),
                    startTime = NOW,
                    phase = "test_phase",
                    message = "test_message",
                    reason = "test_reason",
                    podIP = "127.0.0.1",
                    podIPs = listOf(
                        PodIP("0.0.0.0")
                    ),
                    conditions = listOf(CONDITION),
                    qosClass = "test_qos_class",
                    initContainerStatuses = listOf(CONTAINER_STATUS),
                    containerStatuses = listOf(CONTAINER_STATUS),
                    ephemeralContainerStatuses = listOf(CONTAINER_STATUS),
                    resourceClaimStatuses = listOf(CLAIM_STATUS),
                    resize = "large"
                )
            }
        }

        private fun ContainerStatus.Group.sharedContainerStatus() {
            addContainerStatus {
                imageID = "start"
                image = "test/image"
                name = "test_name"
                ready()
                allocatedResources {
                    put("resource", "one".quantity())
                }
                allocatedResourceStatus {
                    addResourceStatus {
                        name = "resource_status_name"
                        image = "test/image"
                        imageID = "test_image"
                        resources {
                            addResourceHealth {
                                resourceID = "1"
                                health = ResourceHealth.Type.Healthy
                            }
                        }
                        containerID = "test_container_id"
                    }
                }
                containerID = "test_container_id"
                lastState { sharedContainerState() }
                resources {
                    claims {
                        addContainerResourceClaim {
                            name = "claim-name"
                            request = "100m"
                        }
                    }

                    limits {
                        put("cpu", "100m".quantity())
                    }

                    requests {
                        put("cpu", "100m".quantity())
                    }

                    resizePolicy {
                        addResizePolicy {
                            resourceName = "cpu"
                            restartPolicy = "Always"
                        }
                    }
                }
                restartCount = 1
                started()
                state { sharedContainerState() }
                user {
                    linux {
                        gid = 1
                        uid = 2
                        supplementalGroups(3, 4)
                    }
                }
                volumeMounts {
                    mountPath = "/test"
                    name = "test_volume"
                    readOnly()
                    recursiveReadOnly = "read_only"
                }
            }
        }

        private fun ContainerStateDslBuilder.sharedContainerState() {
            running {
                startedAt = NOW
            }

            terminated {
                containerID = "test_container_id"
                exitCode = 1
                finishedAt = NOW
                message = "test_message_terminated"
                reason = "test_reason_terminated"
                signal = 2
                startedAt = NOW
            }

            waiting {
                message = "test_wait"
                reason = "test_wait_reason"
            }
        }
    }
}