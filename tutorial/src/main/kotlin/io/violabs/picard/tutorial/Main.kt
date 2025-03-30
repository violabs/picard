package io.violabs.picard.tutorial

import io.violabs.picard.tutorial.simplePod.SimplePodFactory.buildSimplePod
import io.violabs.picard.tutorial.simplePod.SimplePodFactory.buildSimplePodDsl
import java.io.File

fun main(vararg args: String) {
    println("Creating a simple pod\n")
//    println(YAML_EXAMPLE)
    val simplePodFile = buildSimplePod()

    val file1 = File("./tutorial/src/main/resources/generated/simple-pod.yaml")

    file1.writeText(simplePodFile)

    println("Object based")
    println(simplePodFile)

    val simplePodDslFile = buildSimplePodDsl()

    val file2 = File("./tutorial/src/main/resources/generated/simple-pod-dsl.yaml")

    file2.writeText(simplePodDslFile)

    println("\nDSL")
    println(simplePodDslFile)
}