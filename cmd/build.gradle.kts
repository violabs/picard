import io.violabs.plugins.open.secrets.gradleloader.domain.getPropertyOrEnv

val cmdVersion: String by rootProject.extra

plugins {
    `maven-publish`
    id("io.violabs.plugins.open.publishing.maven-generated-artifacts")
    id("io.violabs.plugins.open.publishing.digital-ocean-spaces")
}

application {
    mainClass.set("io.violabs.picard.cmd.CmdMainKt")
}

group = "io.violabs.picard"
version = cmdVersion

dependencies {
    implementation(project(":common"))
}

tasks.jar {
    archiveBaseName.set("cmd")
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


digitalOceanSpacesPublishing {
    bucket = "open-reliquary"
    accessKey = project.getPropertyOrEnv("spaces.key", "DO_SPACES_API_KEY")
    secretKey = project.getPropertyOrEnv("spaces.secret", "DO_SPACES_SECRET")
    publishedVersion = version.toString()
    dryRun = false
}

mavenGeneratedArtifacts {
    publicationName = "digitalOceanSpaces"
    name = "Picard Command"
    description = """
            Convenience command line tool for Picard. This is only for local development and testing currently.
        """
    websiteUrl = "https://github.com/violabs/picard/tree/main/cmd"

    licenses {
        license {
            name = "Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        }
    }

    developers {
        developer {
            id = "violabs"
            name = "Violabs Team"
            email = "support@violabs.io"
            organization = "Violabs Software"
        }
    }

    scm {
        connection = "https://github.com/violabs/picard.git"
    }
}