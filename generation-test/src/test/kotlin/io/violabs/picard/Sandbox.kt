package io.violabs.picard

import io.violabs.picard.domain.k8sResources.workload.pod.container.Container
import org.junit.jupiter.api.Test

class Sandbox {

    @Test
    fun testSandbox() {
        val manifest = Container.Builder().apply {
            name = "sandbox"
            command("Hello,", "Universe")
        }.build()

        println(YamlBuilder.singleBuild(manifest))
    }
}