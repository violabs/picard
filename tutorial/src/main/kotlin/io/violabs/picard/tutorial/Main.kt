package io.violabs.picard.tutorial

import io.violabs.picard.common.Logger
import io.violabs.picard.common.Logging
import io.violabs.picard.tutorial.kubletConfig.SimpleKubeletConfigurationFactory
import io.violabs.picard.tutorial.podTemplate.PodTemplateFactory.buildPodTemplateDsl
import io.violabs.picard.tutorial.simplePod.SimplePodFactory.buildSimplePod
import io.violabs.picard.tutorial.simplePod.SimplePodFactory.buildSimplePodDsl
import java.io.File

private val logger = Logger(Logging.TUTORIAL)

fun main(vararg args: String) {
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
}

private fun addFile(label: String, fileName: String, content: String) {
    val file = File(fileName)
    file.writeText(content)

    logger.info("$label\n$content\n")
}