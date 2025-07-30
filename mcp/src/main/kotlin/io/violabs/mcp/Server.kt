package io.violabs.mcp

import io.ktor.utils.io.streams.*
import io.modelcontextprotocol.kotlin.sdk.*
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.StdioServerTransport
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.buffered
import kotlinx.serialization.json.*

// Main function to run the MCP server
fun `run mcp server`() {
    // Create the MCP Server instance with a basic implementation
    val server = Server(
        Implementation(
            name = "gradle", // Tool name is "weather"
            version = "1.0.0" // Version of the implementation
        ),
        ServerOptions(
            capabilities = ServerCapabilities(tools = ServerCapabilities.Tools(listChanged = true))
        )
    )

    // Register a tool to fetch weather alerts by state
    server.addTool(
        name = "gradlew_clean",
        description = """
            Clean the gradle project
        """.trimIndent(),
        inputSchema = Tool.Input(
            properties = buildJsonObject {
                putJsonObject("location") {
                    put("type", "string")
                    put("description", "the location of the gradle project, if not specified, current directory is used")
                }
                putJsonObject("module") {
                    put("type", "string")
                    put("description", "if there is a specific module to clean, specify it here, otherwise omit it")
                }
            }
        )
    ) { request ->
        val location = request.arguments["location"]?.jsonPrimitive?.content ?: System.getProperty("user.dir")
        val module = request.arguments["module"]?.jsonPrimitive?.content

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

            CallToolResult(content = response)
        } catch (e: Exception) {
            CallToolResult(
                content = listOf(
                    TextContent("Error executing gradle clean: ${e.message}"),
                    TextContent("Make sure gradlew exists in the project directory and is executable")
                )
            )
        }
    }

    // Create a transport using standard IO for server communication
    val transport = StdioServerTransport(
        System.`in`.asInput(),
        System.out.asSink().buffered()
    )

    runBlocking {
        server.connect(transport)
        val done = Job()
        server.onClose {
            done.complete()
        }
        done.join()
    }
}