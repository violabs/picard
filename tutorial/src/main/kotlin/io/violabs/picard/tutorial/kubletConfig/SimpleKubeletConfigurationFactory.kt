package io.violabs.picard.tutorial.kubletConfig

import io.violabs.picard.buildKubeletConfigYamlContent
import io.violabs.picard.buildKubeletConfiguration
import io.violabs.picard.domain.Kind
import io.violabs.picard.domain.k8sResources.KubeletConfiguration

object SimpleKubeletConfigurationFactory {
    fun buildFromDsl(): String {
        val config = buildKubeletConfiguration {
            apiVersion {
                definedVersion = KubeletConfiguration.DefinedVersion.V1_BETA1
            }
            kind = Kind.KUBELET_CONFIGURATION
            address = "192.168.0.8"
            port = 20250
            serializeImagePulls = false
            evictionHard {
                memory {
                    available = "100Mi"
                }
                nodeFs {
                    available = "10%"
                    iNodesFree = "5%"
                }
                imageFs {
                    available = "15%"
                    iNodesFree = "5%"
                }
            }
            crashLoopBackOff {
                maxContainerRestartPeriod = "100s"
            }
        }

        return buildKubeletConfigYamlContent(config)
    }
}