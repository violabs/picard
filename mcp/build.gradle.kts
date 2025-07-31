plugins {
    kotlin("plugin.serialization") version "2.1.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.google.devtools.ksp")
}

group = "io.violabs"
version = "0.1.0"

val mcpVersion = "0.4.0"
val slf4jVersion = "2.0.9"
val ktorVersion = "3.1.1"

val dslVersion: String by project
val metaDslVersion: String by project

application {
    mainClass.set("io.violabs.mcp.MainKt")
}

dependencies {
    implementation("io.modelcontextprotocol:kotlin-sdk:$mcpVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    ksp("io.violabs.konstellation:dsl:$dslVersion")
    implementation("io.violabs.konstellation:meta-dsl:$metaDslVersion")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.1")
}

tasks.test {
    useJUnitPlatform()
}


ksp {
    arg("projectRootClasspath", "io.violabs.mcp")
    arg("dslBuilderClasspath", "io.violabs.mcp.common")
    arg("dslMarkerClass", "io.violabs.mcp.common.McpDsl")
}