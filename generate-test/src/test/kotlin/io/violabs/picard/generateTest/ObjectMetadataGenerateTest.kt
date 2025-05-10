package io.violabs.picard.generateTest

import io.violabs.geordi.UnitSim
import io.violabs.picard.YamlBuilder
import io.violabs.picard.buildManifest
import org.junit.jupiter.api.Test

class ObjectMetadataGenerateTest : UnitSim() {


    @Test
    fun `generates full object metadata`() = test(horizontalLogs = true) {
        given {
            expect {
                """
                    |apiVersion: v1
                    |kind: Pod
                    |metadata:
                    |  name: test
                    |  namespace: test
                    |  annotations:
                    |    example: "annotation"
                    |    nginx.ingress.kubernetes.io/proxy-read-timeout: "300"
                    |
                """.trimMargin().replace(" ", "•")
            }

            whenever {
                val manifest = buildManifest {
                    workloadSection {
                        pod {
                            metadata {
                                name = "test"
                                namespace = "test"
                                annotations {
                                    add("example", "annotation")
                                    add("nginx.ingress.kubernetes.io/proxy-read-timeout", "300")
                                }
                            }
                        }
                    }
                }

                YamlBuilder.build(manifest).replace(" ", "•")
            }
        }
    }
}