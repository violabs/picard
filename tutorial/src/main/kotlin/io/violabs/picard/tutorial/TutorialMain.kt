package io.violabs.picard.tutorial

import io.violabs.picard.common.DefaultLogger
import io.violabs.picard.common.Logger
import io.violabs.picard.common.Logging
import io.violabs.picard.common.VLoggable
import io.violabs.picard.tutorial.kubletConfig.SimpleKubeletConfigurationFactory
import io.violabs.picard.tutorial.pod.ComplexPodFactory
import io.violabs.picard.tutorial.podTemplate.PodTemplateFactory.buildPodTemplateDsl
import io.violabs.picard.tutorial.pod.SimplePodFactory.buildSimplePod
import io.violabs.picard.tutorial.pod.SimplePodFactory.buildSimplePodDsl
import java.io.File

fun main(vararg args: String) = with(FileManager()) {

    addFile(
        "Simple pod",
        "./tutorial/src/main/resources/generated/simple-pod.yaml",
        buildSimplePod()
    )

    addFile(
        "Simple pod DSL",
        "./tutorial/src/main/resources/generated/simple-pod-dsl.yaml",
        buildSimplePodDsl()
    )

    addFile(
        "Pod template DSL",
        "./tutorial/src/main/resources/generated/pod-template-dsl.yaml",
        buildPodTemplateDsl()
    )

    addFile(
        "Simple kubelet config",
        "./tutorial/src/main/resources/generated/simple-kubelet-config-dsl.yaml",
        SimpleKubeletConfigurationFactory.buildFromDsl()
    )

    addFile(
        "Pod with init containers",
        "./tutorial/src/main/resources/generated/pod-with-init-containers.yaml",
        ComplexPodFactory.buildWithInitContainers()
    )

    addFile(
        "Pod with sidecar",
        "./tutorial/src/main/resources/generated/pod-sidecar.yaml",
        ComplexPodFactory.buildWithSideCar()
    )
}

class FileManager : DefaultLogger("TUTORIAL") {
    fun addFile(label: String, fileName: String, content: String) {
        val file = File(fileName)
        file.writeText(content)

        log.info("$label")
        content.split("\n").forEach { log.info("  | $it") }
    }
}
