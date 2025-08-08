package io.violabs.picard.v2.resources.cluster.node

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NodeSystemInfoTest : FailureBuildSim<NodeSystemInfo, NodeSystemInfoDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NodeSystemInfoTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NodeSystemInfo, NodeSystemInfoDslBuilder> {
            requireScenario("architecture") {
                given(NodeSystemInfoDslBuilder())
            }

            requireScenario("bootID") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                }
            }

            requireScenario("containerRuntimeVersion") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                }
            }

            requireScenario("kernelVersion") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeProxyVersion") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeletVersion") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                }
            }

            requireScenario("machineID") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                }
            }

            requireScenario("operatingSystem") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineId = PLACEHOLDER
                }
            }

            requireScenario("osImage") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineId = PLACEHOLDER
                    operatingSystem = PLACEHOLDER
                }
            }

            requireScenario("systemUUID") {
                given(NodeSystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootId = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineId = PLACEHOLDER
                    operatingSystem = PLACEHOLDER
                    osImage = PLACEHOLDER
                }
            }
        }
    }
}