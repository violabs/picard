package io.violabs.picard.v2.resources.cluster.node

import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.cluster.node.config.ConfigMapNodeConfigSource
import io.violabs.picard.v2.resources.cluster.node.config.NodeConfigSource
import io.violabs.picard.v2.resources.cluster.node.config.NodeConfigStatus
import io.violabs.picard.v2.resources.cluster.node.daemon.DaemonEndpoint
import io.violabs.picard.v2.resources.cluster.node.daemon.NodeDaemonEndpoints
import io.violabs.picard.v2.resources.cluster.node.runtime.NodeRuntimeHandler
import io.violabs.picard.v2.resources.cluster.node.runtime.NodeRuntimeHandlerFeatures
import org.junit.jupiter.api.BeforeAll

class NodeTest : SuccessBuildSim<Node, NodeV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NODE_CONFIG_SOURCE = NodeConfigSource(
            configMap = ConfigMapNodeConfigSource(
                kubeletConfigKey = PLACEHOLDER,
                name = PLACEHOLDER,
                namespace = PLACEHOLDER,
                resourceVersion = PLACEHOLDER,
                uid = PLACEHOLDER
            )
        )

        private val SPEC = NodeSpec(
            configSource = NODE_CONFIG_SOURCE,
            externalId = PLACEHOLDER,
            podCidr = PLACEHOLDER,
            providerId = PLACEHOLDER,
            taints = listOf(Taint(effect = "NoExecute", key = PLACEHOLDER)),
            unschedulable = true
        )

        private val NODE_CONDITION = NodeCondition(
            status = "True",
            type = PLACEHOLDER,
            lastHeartbeatTime = NOW,
            lastTransitionTime = NOW,
            message = PLACEHOLDER,
            reason = PLACEHOLDER
        )

        private val STATUS = NodeStatus(
            addresses = listOf(NodeAddress(address = PLACEHOLDER, type = PLACEHOLDER)),
            allocatable = mapOf(PLACEHOLDER to QUANTITY),
            capacity = mapOf(PLACEHOLDER to QUANTITY),
            conditions = listOf(NODE_CONDITION),
            config = NodeConfigStatus(
                active = NODE_CONFIG_SOURCE,
                assigned = NODE_CONFIG_SOURCE,
                error = PLACEHOLDER,
                lastKnownGood = NODE_CONFIG_SOURCE
            ),
            daemonEndpoints = NodeDaemonEndpoints(
                kubeletEndpoint = DaemonEndpoint(port = 8080)
            ),
            features = NodeFeatures(
                supplementalGroupsPolicy = true
            ),
            images = listOf(
                ContainerImage(
                    names = listOf(PLACEHOLDER),
                    sizeBytes = 1
                )
            ),
            nodeInfo = NodeSystemInfo(
                architecture = PLACEHOLDER,
                bootId = PLACEHOLDER,
                containerRuntimeVersion = PLACEHOLDER,
                kernelVersion = PLACEHOLDER,
                kubeProxyVersion = PLACEHOLDER,
                kubeletVersion = PLACEHOLDER,
                machineId = PLACEHOLDER,
                operatingSystem = PLACEHOLDER,
                osImage = PLACEHOLDER,
                systemUuid = PLACEHOLDER
            ),
            phase = PLACEHOLDER,
            runtimeHandlers = listOf(
                NodeRuntimeHandler(
                    features = NodeRuntimeHandlerFeatures(
                        recursiveReadOnlyMounts = true,
                        userNamespaces = true
                    ),
                    name = PLACEHOLDER
                )
            ),
            volumesAttached = listOf(
                AttachedVolume(
                    devicePath = PLACEHOLDER,
                    name = PLACEHOLDER
                )
            ),
            volumesInUse = listOf(PLACEHOLDER)
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Node, NodeV2DslBuilder> {
            scenario {
                id = "minimum"
                given(NodeV2DslBuilder())
                expected = Node()
            }

            scenario {
                id = "full"
                given(NodeV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        configSource {
                            configMap {
                                kubeletConfigKey = PLACEHOLDER
                                name = PLACEHOLDER
                                namespace = PLACEHOLDER
                                resourceVersion = PLACEHOLDER
                                uid = PLACEHOLDER
                            }
                        }
                        externalId = PLACEHOLDER
                        podCidr = PLACEHOLDER
                        providerId = PLACEHOLDER
                        taints {
                            taint {
                                effect = "NoExecute"
                                key = PLACEHOLDER
                            }
                        }
                        unschedulable()
                    }
                    status {
                        addresses {
                            nodeAddress {
                                address = PLACEHOLDER
                                type = PLACEHOLDER
                            }
                        }
                        allocatable(PLACEHOLDER to QUANTITY)
                        capacity(PLACEHOLDER to QUANTITY)
                        conditions {
                            nodeCondition {
                                status = "True"
                                type = PLACEHOLDER
                                lastHeartbeatTime = NOW
                                lastTransitionTime = NOW
                                message = PLACEHOLDER
                                reason = PLACEHOLDER
                            }
                        }
                        config {
                            active {
                                configMap {
                                    kubeletConfigKey = PLACEHOLDER
                                    name = PLACEHOLDER
                                    namespace = PLACEHOLDER
                                    resourceVersion = PLACEHOLDER
                                    uid = PLACEHOLDER
                                }
                            }
                            assigned {
                                configMap {
                                    kubeletConfigKey = PLACEHOLDER
                                    name = PLACEHOLDER
                                    namespace = PLACEHOLDER
                                    resourceVersion = PLACEHOLDER
                                    uid = PLACEHOLDER
                                }
                            }
                            error = PLACEHOLDER
                            lastKnownGood {
                                configMap {
                                    kubeletConfigKey = PLACEHOLDER
                                    name = PLACEHOLDER
                                    namespace = PLACEHOLDER
                                    resourceVersion = PLACEHOLDER
                                    uid = PLACEHOLDER
                                }
                            }
                        }
                        daemonEndpoints {
                            kubeletEndpoint {
                                port = 8080
                            }
                        }
                        features {
                            supplementalGroupsPolicy()
                        }
                        images {
                            containerImage {
                                names(PLACEHOLDER)
                                sizeBytes = 1
                            }
                        }
                        nodeInfo {
                            architecture = PLACEHOLDER
                            bootId = PLACEHOLDER
                            containerRuntimeVersion = PLACEHOLDER
                            kernelVersion = PLACEHOLDER
                            kubeProxyVersion = PLACEHOLDER
                            kubeletVersion = PLACEHOLDER
                            machineId = PLACEHOLDER
                            operatingSystem = PLACEHOLDER
                            osImage = PLACEHOLDER
                            systemUuid = PLACEHOLDER
                        }
                        phase = PLACEHOLDER
                        runtimeHandlers {
                            nodeRuntimeHandler {
                                features {
                                    recursiveReadOnlyMounts()
                                    userNamespaces()
                                }
                                name = PLACEHOLDER
                            }
                        }
                        volumesAttached {
                            attachedVolume {
                                devicePath = PLACEHOLDER
                                name = PLACEHOLDER
                            }
                        }
                        volumesInUse(PLACEHOLDER)
                    }
                }
                expected = Node(
                    metadata = OBJECT_META,
                    spec = SPEC,
                    status = STATUS
                )
            }
        }
    }
}