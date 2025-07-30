package io.violabs.mcp

import io.modelcontextprotocol.kotlin.sdk.CallToolRequest
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.TextContent
import io.modelcontextprotocol.kotlin.sdk.Tool
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlin.text.startsWith

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

val CallToolRequest.module: String?
    get() = this.arguments["module"]?.jsonPrimitive?.content

val CallToolRequest.skippableTasks: List<String>
    get() = this.arguments["skippableTasks"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()

object GradleApi {
    fun callGradleClean(request: CallToolRequest): CallToolResult {
        val location = request.location
        val module = request.module

        try {
            val projectDir = java.io.File(location)
            val gradlewFile = java.io.File(projectDir, "gradlew")
            val gradlewBatFile = java.io.File(projectDir, "gradlew.bat")

            val command = when {
                gradlewFile.exists() -> "./gradlew"
                gradlewBatFile.exists() -> "gradlew.bat"
                else -> "gradle" // fallback to system gradle
            }

            val cleanTask = if (module != null) {
                val modulePrefix = if (module.startsWith(":")) module else ":$module"
                "${modulePrefix}:clean"
            } else {
                "clean"
            }

            val process = ProcessBuilder(command, cleanTask)
                .directory(projectDir)
                .redirectErrorStream(true)
                .start()

            val response = process.inputReader().use { reader ->
                reader.lines().map { it.trim() }.map { TextContent(it) }.toList()
            }

            return CallToolResult(content = response)
        } catch (e: Exception) {
            return CallToolResult(
                content = listOf(
                    TextContent("Error executing gradle clean: ${e.message}"),
                    TextContent("Make sure gradlew exists in the project directory and is executable")
                )
            )
        }
    }

    fun callGradleCleanBuild(request: CallToolRequest): CallToolResult {
        val location = request.location
        val module = request.module
        val skippableTasks = request.skippableTasks

        try {
            val projectDir = java.io.File(location)
            val gradlewFile = java.io.File(projectDir, "gradlew")
            val gradlewBatFile = java.io.File(projectDir, "gradlew.bat")

            val command = when {
                gradlewFile.exists() -> "./gradlew"
                gradlewBatFile.exists() -> "gradlew.bat"
                else -> "gradle" // fallback to system gradle
            }

            val args = mutableListOf<String>()

            if (module != null) {
                val modulePrefix = if (module.startsWith(":")) module else ":$module"
                args.add("${modulePrefix}:clean")
                args.add("${modulePrefix}:build")
                skippableTasks.forEach { task ->
                    args.add("-x")
                    args.add("${modulePrefix}:${task}")
                }
            } else {
                args.add("clean")
                args.add("build")
                skippableTasks.forEach { task ->
                    args.add("-x")
                    args.add(task)
                }
            }

            val processBuilder = ProcessBuilder(listOf(command) + args)
                .directory(projectDir)
                .redirectErrorStream(true)

            val process = processBuilder.start()

            val response = process.inputReader().use { reader ->
                reader.lines().map { it.trim() }.map { TextContent(it) }.toList()
            }

            return CallToolResult(content = response)
        } catch (e: Exception) {
            return CallToolResult(
                content = listOf(
                    TextContent("Error executing gradle clean: ${e.message}"),
                    TextContent("Make sure gradlew exists in the project directory and is executable")
                )
            )
        }
    }
}
