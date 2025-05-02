package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.Taint
import io.violabs.picard.domain.k8sResources.cluster.DaemonEndpoint
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeTest : SuccessBuildSim<Node, Node.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val NODE_CONFIG_SOURCE = NodeConfigSource(
            configMap = NodeConfigSourceConfigMap(
                kubeletConfigKey = PLACEHOLDER,
                name = PLACEHOLDER,
                namespace = PLACEHOLDER,
                resourceVersion = PLACEHOLDER,
                uid = PLACEHOLDER
            )
        )

        private val SPEC = Node.Spec(
            configSource = NODE_CONFIG_SOURCE,
            externalID = PLACEHOLDER,
            podCIDR = PLACEHOLDER,
            providerID = PLACEHOLDER,
            taints = listOf(Taint(Taint.Effect.NoExecute)),
            value = true
        )

        private val STATUS = Node.Status(
            addresses = listOf(NodeAddress(PLACEHOLDER, PLACEHOLDER)),
            allocatable = mapOf(PLACEHOLDER to QUANTITY),
            capacity = mapOf(PLACEHOLDER to QUANTITY),
            conditions = listOf(NODE_CONDITION),
            config = NodeConfigStatus(
                active = NODE_CONFIG_SOURCE,
                assigned = NODE_CONFIG_SOURCE,
                error = PLACEHOLDER,
                lastKnownGood = NODE_CONFIG_SOURCE
            ),
            daemonEndpoints = DaemonEndpoints(
                kubeletEndpoint = DaemonEndpoint(8080)
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
            nodeInfo = SystemInfo(
                architecture = PLACEHOLDER,
                bootID = PLACEHOLDER,
                containerRuntimeVersion = PLACEHOLDER,
                kernelVersion = PLACEHOLDER,
                kubeProxyVersion = PLACEHOLDER,
                kubeletVersion = PLACEHOLDER,
                machineID = PLACEHOLDER,
                operatingSystem = PLACEHOLDER,
                osImage = PLACEHOLDER,
                systemUUID = PLACEHOLDER
            ),
            phase = PLACEHOLDER,
            runtimeHandlers = listOf(
                RuntimeHandler(
                    features = RuntimeHandlerFeatures(
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

        private fun NodeConfigSource.Builder.defaultNodeConfigSource() {
            configMap {
                kubeletConfigKey = PLACEHOLDER
                name = PLACEHOLDER
                namespace = PLACEHOLDER
                resourceVersion = PLACEHOLDER
                uid = PLACEHOLDER
            }
        }

        private val SUCCESS_POSSIBILITIES = possibilities<Node, Node.Builder> {
            scenario {
                id = "minimum"
                given(Node.Builder())
                expected = Node()
            }

            scenario {
                id = "full"
                given(Node.Builder()) {
                    sharedMetadata()
                    spec {
                        configSource {
                            defaultNodeConfigSource()
                        }
                        externalID = PLACEHOLDER
                        podCIDR = PLACEHOLDER
                        providerID = PLACEHOLDER
                        taints {
                            taint { effect = Taint.Effect.NoExecute }
                        }
                        value()
                    }

                    this.status {
                        addresses {
                            address {
                                address = PLACEHOLDER
                                type = PLACEHOLDER
                            }
                        }
                        allocatable(PLACEHOLDER to QUANTITY)
                        capacity(PLACEHOLDER to QUANTITY)
                        conditions {
                            sharedNodeCondition()
                        }
                        config {
                            active { defaultNodeConfigSource() }
                            assigned { defaultNodeConfigSource() }
                            error = PLACEHOLDER
                            lastKnownGood { defaultNodeConfigSource() }
                        }
                        daemonEndpoints {
                            daemonEndpoint { port = 8080 }
                        }
                        features {
                            supplementalGroupsPolicy()
                        }
                        images {
                            image {
                                names(PLACEHOLDER)
                                sizeBytes = 1
                            }
                        }
                        nodeInfo {
                            architecture = PLACEHOLDER
                            bootID = PLACEHOLDER
                            containerRuntimeVersion = PLACEHOLDER
                            kernelVersion = PLACEHOLDER
                            kubeProxyVersion = PLACEHOLDER
                            kubeletVersion = PLACEHOLDER
                            machineID = PLACEHOLDER
                            operatingSystem = PLACEHOLDER
                            osImage = PLACEHOLDER
                            systemUUID = PLACEHOLDER
                        }
                        phase = PLACEHOLDER
                        runtimeHandlers {
                            handler {
                                features {
                                    recursiveReadOnlyMounts()
                                    userNamespaces()
                                }
                                name = PLACEHOLDER
                            }
                        }
                        volumesAttached {
                            volume {
                                devicePath = PLACEHOLDER
                                name = PLACEHOLDER
                            }
                        }
                        volumesInUse(PLACEHOLDER)
                    }
                }
                expected = Node(
                    metadata = METADATA,
                    spec = SPEC,
                    status = STATUS
                )
            }
        }
    }
}