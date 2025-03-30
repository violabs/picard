

application {
    mainClass.set("io.violabs.picard.cmd.MainKt")
}

tasks.register<Exec>("runPod") {
    val podFilePath = project
        .findProperty("pod-file-path") as String?
        ?: throw IllegalArgumentException("item is not set")

    commandLine("kubectl", "apply", "-f", podFilePath)
}

// BASIC
//# List all pods in the current namespace
//kubectl get pods
//
//# List all pods in all namespaces
//kubectl get pods --all-namespaces
//# or the shorter version
//kubectl get pods -A
//
//# Get more details about the pods
//kubectl get pods -o wide
//
//# Get details about a specific pod
//kubectl get pod <pod-name>
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