package io.violabs.picard.tutorial

import io.violabs.picard.tutorial.simplePod.SimplePodFactory.buildSimplePod
import java.io.File

fun main(vararg args: String) {
    println("Creating a simple pod\n")
//    println(YAML_EXAMPLE)
    val simplePodFile = buildSimplePod()

    val file = File("./tutorial/src/main/resources/generated/simple-pod.yaml")

    file.writeText(simplePodFile)

    println(simplePodFile)
}