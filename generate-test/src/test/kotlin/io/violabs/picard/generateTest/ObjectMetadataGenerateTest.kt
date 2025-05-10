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
                    |    example: annotation
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