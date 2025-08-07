package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SystemInfoTest : FailureBuildSim<SystemInfo, SystemInfoDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SystemInfoTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SystemInfo, SystemInfoDslBuilder> {
            requireScenario("architecture") {
                given(SystemInfoDslBuilder())
            }

            requireScenario("bootID") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                }
            }

            requireScenario("containerRuntimeVersion") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                }
            }

            requireScenario("kernelVersion") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeProxyVersion") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeletVersion") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                }
            }

            requireScenario("machineID") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                }
            }

            requireScenario("operatingSystem") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineID = PLACEHOLDER
                }
            }

            requireScenario("osImage") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineID = PLACEHOLDER
                    operatingSystem = PLACEHOLDER
                }
            }

            requireScenario("systemUUID") {
                given(SystemInfoDslBuilder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                    machineID = PLACEHOLDER
                    operatingSystem = PLACEHOLDER
                    osImage = PLACEHOLDER
                }
            }
        }
    }
}