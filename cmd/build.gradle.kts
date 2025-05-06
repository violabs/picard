plugins {
    `maven-publish`
}

application {
    mainClass.set("io.violabs.picard.cmd.CmdMainKt")
}

group = "io.violabs.picard"
version = "0.0.1"

dependencies {
    implementation(project(":common"))
}

publishing {
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId    = "io.violabs.picard"
            artifactId = "command"
            version    = version
        }
    }
}


//region REVISIT
tasks.register<Exec>("runPod") {
    val podFilePath = project
        .findProperty("pod-file-path") as String?
        ?: throw IllegalArgumentException("item is not set")

    commandLine("kubectl", "apply", "-f", podFilePath)
}

tasks.register("listPods") {
    val allNamespaces: Boolean = derive(false,  "search-all-namespaces", "all-namespaces", "A")
    val wide: Boolean = derive(false, "wide")

}

inline fun <reified T> derive(default: T, vararg namespaces: String): T {
    val property = namespaces.firstNotNullOfOrNull { project.findProperty(it) as T? }

    return property ?: default
}

/**
 * Order of precedence:
 * 1. Property from command line explicitly set
 * 2. Property from command line with no value (implies true)
 * 3. Default value
 */
fun derive(default: Boolean, vararg namespaces: String): Boolean {
    val anyExist = namespaces.any(project::hasProperty)

    val foundValue = namespaces.firstNotNullOfOrNull { project.findProperty(it) as Boolean? }

    return foundValue ?: anyExist || default
}

open class ListPods : DefaultTask() {
    @Input
    @set:Option(option = "all-namespaces", description = "Search in all namespaces")
    @get:Optional
    var allNamespaces: Boolean = false
}

open class TestTask : DefaultTask() {
    @Input
    @set:Option(option = "flagExample", description = "The test configuration")
    @get:Optional
    var flagExample: String? = null

    @TaskAction
    fun run() {
        println("Test value: $flagExample")
    }
}

tasks.register<TestTask>("runTest")
//endregion REVISIT

tasks.register("listLocalMaven") {
    doLast {
        val repo = file("${System.getProperty("user.home")}/.m2/repository")
        repo.walkTopDown()
            .filter { it.extension == "pom" }
            .map {
                it.relativeTo(repo)
                    .invariantSeparatorsPath
                    .removeSuffix(".pom")
                    .replace('/', ':')
            }
            .distinct()
            .sorted()
            .forEach(::println)
    }
}