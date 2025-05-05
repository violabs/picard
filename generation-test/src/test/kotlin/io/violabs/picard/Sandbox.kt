package io.violabs.picard

import org.junit.jupiter.api.Test

class Sandbox {

    @Test
    fun testSandbox() {
        val manifest = buildManifest {
            config {
                configMap {

                }
            }

            workload {
                pod {  }
            }
        }

        println(YamlBuilder.build(manifest))
    }
}