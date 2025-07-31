package io.violabs.mcp

import io.modelcontextprotocol.kotlin.sdk.CallToolRequest
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.TextContent
import io.modelcontextprotocol.kotlin.sdk.Tool
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import java.io.File

fun JsonObjectBuilder.location(
    description: String = "the location of the gradle project, if not specified, current directory is used"
) {
    putJsonObject("location") {
        put("type", "string")
        put("description", description)
    }
}

fun JsonObjectBuilder.module(
    description: String = "if there is a specific module to clean, specify it here, otherwise omit it"
) {
    putJsonObject("module") {
        put("type", "string")
        put("description", description)
    }
}

object GradleToolInputs {
    val BASE_REQUEST = Tool.Input(
        properties = buildJsonObject {
            location()
            module()
        }
    )

    val SKIPPABLE_REQUEST = Tool.Input(
        properties = buildJsonObject {
            location()
            module()
            putJsonObject("skippableTasks") {
                put("type", "array")
                putJsonObject("items") {
                    put("type", "string")
                }
                put("description", "list of tasks to skip (e.g., ['test', 'integrationTest'])")
            }
        }
    )
}

val CallToolRequest.location: String
    get() = this.arguments["location"]?.jsonPrimitive?.content ?: System.getProperty("user.dir")

val CallToolRequest.module: String
    get() = this.arguments["module"]?.jsonPrimitive?.content?.let { "$it:" } ?: ""

val CallToolRequest.skippableTasks: List<String>
    get() = this.arguments["skippableTasks"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()

@GeneratedDsl(isRoot = true)
data class GradleCommand(
    val projectPath: String,
    val runnableTasks: List<String>,
    val module: String = "",
    val skippableTasks: List<String>? = null
) {
    private fun projectDir(): File = File(projectPath)
    private fun command(): String = determineGradleCommand()
    private fun args(): List<String> = extrapolateArgs()

    private fun determineGradleCommand(): String {
        val projectDir = projectDir()
        val gradlewFile = File(projectDir, "gradlew")
        val gradlewBatFile = File(projectDir, "gradlew.bat")
        return when {
            gradlewFile.exists() -> "./gradlew"
            gradlewBatFile.exists() -> "gradlew.bat"
            else -> "gradle" // fallback to system gradle
        }
    }

    /**
     * Extrapolates the arguments for the Gradle command based on the provided tasks and skippable tasks.
     * If the module is specified, it prefixes the tasks with the module name.
     * e.g. if the module is "app" and the tasks are "build" and "test", it returns ["app:build", "app:test"].
     */
    private fun extrapolateArgs(): List<String> = mutableListOf<String>().apply {
        runnableTasks.forEach { task -> add("${module}$task") }

        skippableTasks?.forEach { task ->
            add("-x")
            add(task)
        }
    }

    private fun processCommand(
        projectDir: File,
        command: String,
        args: List<String>
    ): List<TextContent> {
        val processBuilder = ProcessBuilder(listOf(command) + args)
            .directory(projectDir)
            .redirectErrorStream(true)

        val process = processBuilder.start()

        return process.inputReader().use { reader ->
            reader.lines().map { it.trim() }.map { TextContent(it) }.toList()
        }
    }

    fun result(): CallToolResult {
        val response = try {
            processCommand(projectDir(), command(), args())
        } catch (e: Exception) {
            listOf(
                TextContent("Error executing command: ${e.message}"),
                TextContent("Make sure the command is correct and executable")
            )
        }
        return CallToolResult(content = response)
    }
}

object GradleApi {
    fun callGradleClean(request: CallToolRequest): CallToolResult = gradleCommand {
        projectPath = request.location
        runnableTasks("clean")
        module = request.module
    }.result()

    fun callGradleCleanBuild(request: CallToolRequest): CallToolResult = gradleCommand {
        projectPath = request.location
        runnableTasks("clean", "build")
        module = request.module
        skippableTasks(*request.skippableTasks.toTypedArray())
    }.result()

    fun callGradleTest(request: CallToolRequest): CallToolResult = gradleCommand {
        projectPath = request.location
        runnableTasks("test")
        module = request.module
        skippableTasks(*request.skippableTasks.toTypedArray())
    }.result()
}
