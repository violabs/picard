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
        inputSchema = GradleToolInputs.BASE_REQUEST,
        GradleApi::callGradleClean
    )

    server.addTool(
        name = "gradlew_clean_build",
        description = """
            Clean and build the gradle project. You can specify whether to run the tests or not.
        """.trimIndent(),
        inputSchema = GradleToolInputs.SKIPPABLE_REQUEST,
        GradleApi::callGradleCleanBuild
    )

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