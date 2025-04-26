package io.violabs.picard.domain.k8sResources.cluster.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class SystemInfoTest : FailureBuildSim<SystemInfo, SystemInfo.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SystemInfoTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<SystemInfo, SystemInfo.Builder> {
            requireScenario("architecture") {
                given(SystemInfo.Builder())
            }

            requireScenario("bootID") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                }
            }

            requireScenario("containerRuntimeVersion") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                }
            }

            requireScenario("kernelVersion") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeProxyVersion") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                }
            }

            requireScenario("kubeletVersion") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                }
            }

            requireScenario("machineID") {
                given(SystemInfo.Builder()) {
                    architecture = PLACEHOLDER
                    bootID = PLACEHOLDER
                    containerRuntimeVersion = PLACEHOLDER
                    kernelVersion = PLACEHOLDER
                    kubeProxyVersion = PLACEHOLDER
                    kubeletVersion = PLACEHOLDER
                }
            }

            requireScenario("operatingSystem") {
                given(SystemInfo.Builder()) {
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
                given(SystemInfo.Builder()) {
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
                given(SystemInfo.Builder()) {
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